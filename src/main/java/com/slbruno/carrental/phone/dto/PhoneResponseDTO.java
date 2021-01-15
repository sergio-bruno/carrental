package com.slbruno.carrental.phone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponseDTO {

    private Long id;

    private Integer number;

    private Integer areaCode;
    
    private String countryCode;

}
