package com.drex.service.energy.mapper;

import com.drex.service.expose.dto.response.GraphicResponse;
import com.drex.service.energy.model.entity.Graphic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GraphicMapper {

    @Mapping(target = "x.measurementTimeUtc", source = "measurementTimeUtc", qualifiedByName = "timestampResponse")
    @Mapping(target = "y.activePower", source = "activePower")
    @Mapping(target = "y.accumulatedEnergyKwh", source = "accumulatedEnergyKwh")
    @Mapping(target = "y.currentA", source = "currentA")
    @Mapping(target = "y.currentB", source = "currentB")
    @Mapping(target = "y.currentC", source = "currentC")
    GraphicResponse toResponse(Graphic Graphic);


    @Named("timestampResponse")
    default OffsetDateTime timestampResponse(LocalDateTime localDateTime) {
        return localDateTime.atOffset(ZoneOffset.UTC);
    }

}
