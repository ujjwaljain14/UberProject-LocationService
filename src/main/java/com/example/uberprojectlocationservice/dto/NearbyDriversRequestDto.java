package com.example.uberprojectlocationservice.dto;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class NearbyDriversRequestDto {
    double latitude;
    double longitude;
}
