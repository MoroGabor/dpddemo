package com.dpd.demo.web.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {

    @Min(value = 1000, message = "Post code must be at least 1000")
    @Max(value = 9999, message = "Post code must be at most 9999")
    private int postCode;

    @NotBlank(message = "City must not be blank")
    private String city;

    @NotBlank(message = "Street must not be blank")
    private String street;

    @NotBlank(message = "House number must not be blank")
    private String houseNumber;

}
