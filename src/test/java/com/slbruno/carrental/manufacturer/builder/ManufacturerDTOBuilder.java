package com.slbruno.carrental.manufacturer.builder;

import com.slbruno.carrental.manufacturer.dto.ManufacturerDTO;
import lombok.Builder;

@Builder
public class ManufacturerDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String name = "Fiat";
    
    public ManufacturerDTO buildManufacturerDTO() {
        return new ManufacturerDTO(id, name);
    }
}
