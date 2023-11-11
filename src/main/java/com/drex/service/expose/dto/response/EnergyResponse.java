package com.drex.service.expose.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class EnergyResponse {
    private Double instantEnergyKwh;
    private Double accumulatedEnergyKwh;
    private Double voltageAb;
    private Double voltageBc;
    private Double voltageCa;
    private Double currentA;
    private Double currentB;
    private Double currentC;
    private Double activePower;
    private Double reactivePower;
    private Double apparentPower;
    private Double powerFactor;
    private OffsetDateTime measurementTime;
}
