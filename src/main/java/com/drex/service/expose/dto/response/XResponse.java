package com.drex.service.expose.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
public class XResponse {
    private OffsetDateTime measurementTimeUtc;
}
