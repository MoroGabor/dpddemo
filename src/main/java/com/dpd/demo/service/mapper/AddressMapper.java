package com.dpd.demo.service.mapper;

import com.dpd.demo.persistence.entity.AddressEntity;
import com.dpd.demo.web.dto.request.AddressRequestDto;
import com.dpd.demo.web.dto.response.AddressResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressEntity mapToEntity(AddressRequestDto addressRequestDto) {
        return AddressEntity.builder()
                .postCode(addressRequestDto.getPostCode())
                .city(addressRequestDto.getCity())
                .street(addressRequestDto.getStreet())
                .houseNumber(addressRequestDto.getHouseNumber())
                .build();
    }

    public AddressResponseDto mapToResponse(AddressEntity addressEntity) {
        return AddressResponseDto.builder()
                .postCode(addressEntity.getPostCode())
                .city(addressEntity.getCity())
                .street(addressEntity.getStreet())
                .houseNumber(addressEntity.getHouseNumber())
                .build();
    }

}
