package com.drex.service.energy.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Table(name = "Energy")
@Getter
@Setter
@Builder
public class Energy {

    @Id
    @Column("energy_id")
    private Long id;
    @Column
    private Long operationId;
    @Column
    private Double instantEnergyKwh;
    @Column
    private Double accumulatedEnergyKwh;
    @Column
    private Double voltageAb;
    @Column
    private Double voltageBc;
    @Column
    private Double voltageCa;
    @Column
    private Double currentA;
    @Column
    private Double currentB;
    @Column
    private Double currentC;
    @Column
    private Double activePower;
    @Column
    private Double reactivePower;
    @Column
    private Double apparentPower;
    @Column
    private Double powerFactor;
    @Column
    private OffsetDateTime measurementTime;
}
