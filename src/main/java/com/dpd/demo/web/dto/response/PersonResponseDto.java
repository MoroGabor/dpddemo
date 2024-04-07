package com.dpd.demo.web.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PersonResponseDto {

    private String name;
    private String birthPlace;
    private LocalDate birthDate;
    private String motherName;
    private String socialSecurityNumber;
    private String taxNumber;
    private String email;
    private List<AddressResponseDto> addresses;
    private List<PhoneNumberResponseDto> phoneNumbers;

}
