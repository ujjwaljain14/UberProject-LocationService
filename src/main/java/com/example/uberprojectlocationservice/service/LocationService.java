package com.example.uberprojectlocationservice.service;

import com.example.uberprojectlocationservice.dto.DriverLocationDto;

import java.util.List;

public interface LocationService {
    Boolean saveDriverLocation(String driverId, Double latitude, Double longitude);

    List<DriverLocationDto> getNearbyDrivers(Double latitude, Double longitude);
}
