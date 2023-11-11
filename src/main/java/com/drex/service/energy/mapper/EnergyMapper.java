package com.drex.service.energy.mapper;

import com.drex.service.expose.dto.request.EnergyRequest;
import com.drex.service.expose.dto.response.EnergyResponse;
import com.drex.service.energy.model.entity.Energy;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EnergyMapper {

    @Mapping(target = "currentA", source = "request.ia")
    @Mapping(target = "currentB", source = "request.ib")
    @Mapping(target = "currentC", source = "request.ic")
    @Mapping(target = "voltageAb", source = "request.vab")
    @Mapping(target = "voltageBc", source = "request.vbc")
    @Mapping(target = "voltageCa", source = "request.vca")
    @Mapping(target = "activePower", source = "request.kw")
    @Mapping(target = "reactivePower", source = "request.kvar")
    @Mapping(target = "apparentPower", source = "request.kva")
    @Mapping(target = "powerFactor", source = "request.pf")
    @Mapping(target = "measurementTime", source = "request.posixTimestamp", qualifiedByName = "convertToDateTime")
    Energy toEntity(EnergyRequest request, Long operationId);

    List<Energy> toEntityList(List<EnergyRequest> requests, @Context Long operationId);

    @Mapping(target = "measurementTime", source = "energy.measurementTime", qualifiedByName = "timestampResponse")
    EnergyResponse toResponse(Energy energy);


    @Named("convertToDateTime")
    default OffsetDateTime posixToDateTime(Long posix_timestamp) {
        Instant instant = Instant.ofEpochMilli(posix_timestamp);
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }


    @Named("timestampResponse")
    default OffsetDateTime timestampResponse(OffsetDateTime offsetDateTime) {
        return offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);
    }


    default Energy mapContext(EnergyRequest request, @Context Long operationId) {
        return toEntity(request, operationId);
    }
}
