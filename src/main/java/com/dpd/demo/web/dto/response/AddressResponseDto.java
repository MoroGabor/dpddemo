package com.dpd.demo.web.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponseDto {

    private int postCode;
    private String city;
    private String street;
    private int houseNumber;

}
