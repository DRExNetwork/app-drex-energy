package com.drex.service.expose.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class EnergyRequest {
    @NotNull
    private Double ia;
    @NotNull
    private Double ib;
    @NotNull
    private Double ic;
    @NotNull
    private Double vab;
    @NotNull
    private Double vbc;
    @NotNull
    private Double vca;
    @NotNull
    private Double kw;
    @NotNull
    private Double kvar;
    @NotNull
    private Double kva;
    @NotNull
    private Double pf;
    @NotNull
    private Double accumulatedEnergyKwh;
    @NotNull
    private Double instantEnergyKwh;
    @NotNull
    private Long posixTimestamp;
}
