package com.drex.service.expose.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YResponse {
    private Double activePower;
    private Double accumulatedEnergyKwh;
    private Double currentA;
    private Double currentB;
    private Double currentC;
}
