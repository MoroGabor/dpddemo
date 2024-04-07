package com.dpd.demo.unitTest.mapper;

import com.dpd.demo.persistence.entity.AddressEntity;
import com.dpd.demo.service.mapper.AddressMapper;
import com.dpd.demo.web.dto.request.AddressRequestDto;
import com.dpd.demo.web.dto.response.AddressResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private final AddressMapper underTest = new AddressMapper();

    @Test
    void mapToEntity() {
        // GIVEN
        AddressRequestDto addressRequest = AddressRequestDto.builder()
                .postCode(2345)
                .city("TestCity")
                .street("TestStreet")
                .houseNumber("123")
                .build();

        // WHEN
        AddressEntity actual = underTest.mapToEntity(addressRequest);

        // THEN
        assertEquals(addressRequest.getPostCode(), actual.getPostCode());
        assertEquals(addressRequest.getCity(), actual.getCity());
        assertEquals(addressRequest.getStreet(), actual.getStreet());
        assertEquals(addressRequest.getHouseNumber(), actual.getHouseNumber());
    }

    @Test
    void mapToEntityNull() {
        // GIVEN
        AddressRequestDto addressRequest = null;

        // WHEN
        Executable callMap = () -> underTest.mapToEntity(addressRequest);

        // THEN
        NullPointerException exception = assertThrows(NullPointerException.class, callMap);
        assertNotNull(exception.getMessage());
    }

    @Test
    void mapToResponse() {
        // GIVEN
        AddressEntity addressEntity = AddressEntity.builder()
                .postCode(1234)
                .city("TestCity")
                .street("TestStreet")
                .houseNumber("123")
                .build();

        // WHEN
        AddressResponseDto actual = underTest.mapToResponse(addressEntity);

        // THEN
        assertEquals(addressEntity.getPostCode(), actual.getPostCode());
        assertEquals(addressEntity.getCity(), actual.getCity());
        assertEquals(addressEntity.getStreet(), actual.getStreet());
        assertEquals(addressEntity.getHouseNumber(), actual.getHouseNumber());
    }

}
