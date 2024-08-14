package com.example.uberprojectlocationservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SaveDriverLocationRequestDto {

    String driverId;
    double latitude;
    double longitude;
}
