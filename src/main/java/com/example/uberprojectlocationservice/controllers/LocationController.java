package com.example.uberprojectlocationservice.controllers;

import com.example.uberprojectlocationservice.dto.NearbyDriversRequestDto;
import com.example.uberprojectlocationservice.dto.SaveDriverLocationRequestDto;
import com.example.uberprojectlocationservice.service.LocationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/location")
public class LocationController {


    private final LocationServiceImpl locationServiceImpl;

    public LocationController(LocationServiceImpl locationServiceImpl) {
        this.locationServiceImpl = locationServiceImpl;
    }


    @PostMapping("/drivers")
    public ResponseEntity<Boolean> saveDriverLocation(@RequestBody SaveDriverLocationRequestDto saveDriverLocationRequestDto){

        try {

            return new ResponseEntity<>(  locationServiceImpl.saveDriverLocation(
                    saveDriverLocationRequestDto.getDriverId(),
                    saveDriverLocationRequestDto.getLatitude(),
                    saveDriverLocationRequestDto.getLongitude()
            ),
            HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/nearby/drivers")
    public ResponseEntity<?> getNearbyDrivers(@RequestBody NearbyDriversRequestDto nearbyDriversRequestDto){

        try {
            return new ResponseEntity<>(locationServiceImpl
                    .getNearbyDrivers(
                            nearbyDriversRequestDto.getLatitude(),
                            nearbyDriversRequestDto.getLongitude()
                    ),
                    HttpStatus.OK
            );

        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
