package com.dpd.demo.web.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDto {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Birth place must not be blank")
    private String birthPlace;

    @NotNull(message = "Birth date must not be null")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Mother's name must not be blank")
    private String motherName;

    @NotBlank(message = "Social Security Number must not be blank")
    private String socialSecurityNumber;

    @NotBlank(message = "Tax number must not be blank")
    private String taxNumber;

    @NotBlank(message = "Email must not be blank")
    @Email
    private String email;

    @NotEmpty(message = "Addresses must not be empty")
    @Valid
    private List<AddressRequestDto> addresses;

    @NotEmpty(message = "Phone numbers must not be empty")
    @Valid
    private List<PhoneNumberRequestDto> phoneNumbers;
}
