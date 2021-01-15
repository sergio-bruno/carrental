package com.slbruno.carrental.phone.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequestDTO {

    private Long id;

    private Integer number;

    private Integer areaCode;
    
    @Size(max = 10)
    private String countryCode;
    
}
