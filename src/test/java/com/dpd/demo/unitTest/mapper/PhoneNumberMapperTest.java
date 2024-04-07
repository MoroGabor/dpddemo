package com.dpd.demo.unitTest.mapper;

import com.dpd.demo.persistence.entity.PhoneNumberEntity;
import com.dpd.demo.service.mapper.PhoneNumberMapper;
import com.dpd.demo.web.dto.request.PhoneNumberRequestDto;
import com.dpd.demo.web.dto.response.PhoneNumberResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberMapperTest {

    private final PhoneNumberMapper underTest = new PhoneNumberMapper();

    @Test
    void setAllFieldWithPhoneNumberRequest() {
        // GIVEN
        PhoneNumberRequestDto phoneNumberRequest = PhoneNumberRequestDto.builder()
                .phoneNumber("testName")
                .build();

        // WHEN
        PhoneNumberEntity actual = underTest.mapToEntity(phoneNumberRequest);

        //THEN
        assertEquals(phoneNumberRequest.getPhoneNumber(), actual.getPhoneNumber());
    }

    @Test
    void whenCreatePhoneNumberRequestNull() {
        // GIVEN
        PhoneNumberRequestDto phoneNumberRequest = null;

        // WHEN
        Executable callMap = () -> underTest.mapToEntity(phoneNumberRequest);

        // THEN
        NullPointerException exception = assertThrows(NullPointerException.class, callMap);
        assertNotNull(exception.getMessage());
    }


    @Test
    void setAllFieldWithPhoneNumberResponse() {
        // GIVEN
        PhoneNumberEntity phoneNumberEntity = PhoneNumberEntity.builder()
                .phoneNumber("testPhoneNumber")
                .build();

        // WHEN
        PhoneNumberResponseDto actual = underTest.mapToResponse(phoneNumberEntity);

        // THEN
        assertEquals(phoneNumberEntity.getPhoneNumber(), actual.getPhoneNumber());
    }
}

