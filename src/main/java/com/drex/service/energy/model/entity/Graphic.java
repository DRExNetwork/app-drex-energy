package com.drex.service.energy.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class Graphic {
    private Double activePower;
    private Double accumulatedEnergyKwh;
    private Double currentA;
    private Double currentB;
    private Double currentC;
    private LocalDateTime measurementTimeUtc;
}
