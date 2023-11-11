package com.drex.service.energy.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "Devices")
@Getter
@Setter
@Builder
public class Device {

    @Id
    @Column("device_id")
    private Long id;

    @Column
    private String label;

    @Column
    private String meterCode;

    @Column
    private Double latitude;

    @Column
    private Double longitude;
}
