package com.example.uberprojectlocationservice.service;

import com.example.uberprojectlocationservice.dto.DriverLocationDto;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LocationServiceImpl implements LocationService {

    private final String DRIVER_GEO_OPS_KEY = "drivers";
    private final Double SEARCH_RADIUS = 5.0;
    private final StringRedisTemplate stringRedisTemplate;
    private final DriverLocationDto driverLocationDto;

    public LocationServiceImpl(StringRedisTemplate stringRedisTemplate, DriverLocationDto driverLocationDto) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.driverLocationDto = driverLocationDto;
    }

    @Override
    public Boolean saveDriverLocation(String driverId, Double latitude, Double longitude) {
        GeoOperations<String, String> geoOps = stringRedisTemplate.opsForGeo();

        geoOps.add(
                DRIVER_GEO_OPS_KEY,
                new RedisGeoCommands.GeoLocation<>(
                        driverId,
                        new Point(
                                latitude,
                                longitude
                        )
                )
        );
        return true;
    }

    @Override
    public List<DriverLocationDto> getNearbyDrivers(Double latitude, Double longitude) {
        GeoOperations<String, String> geoOps = stringRedisTemplate.opsForGeo();

        Distance radius = new Distance(SEARCH_RADIUS, Metrics.KILOMETERS);
        Circle within = new Circle(
                new Point(
                        latitude,
                        longitude
                ),
                radius
        );

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOps.radius(DRIVER_GEO_OPS_KEY, within);

        List<DriverLocationDto> drivers = new ArrayList<>();

        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {
            Point point= Objects.requireNonNull(geoOps.position(DRIVER_GEO_OPS_KEY, result.getContent().getName())).get(0);
            drivers.add(
                    driverLocationDto
                            .convertDto(
                                    result.getContent().getName(),
                                    point.getX(),
                                    point.getY()
                            )
            );
        }
        return drivers;
    }
}
