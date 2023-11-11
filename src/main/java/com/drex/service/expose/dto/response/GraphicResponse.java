package com.drex.service.expose.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GraphicResponse {
    private YResponse y;
    private XResponse x;
}
