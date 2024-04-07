package com.dpd.demo.service.mapper;

import com.dpd.demo.persistence.entity.PhoneNumberEntity;
import com.dpd.demo.web.dto.request.PhoneNumberRequestDto;
import com.dpd.demo.web.dto.response.PhoneNumberResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberMapper {

    public PhoneNumberEntity mapToEntity(PhoneNumberRequestDto phoneNumberRequestDto) {
        return PhoneNumberEntity.builder()
                .phoneNumber(phoneNumberRequestDto.getPhoneNumber())
                .build();
    }

    public PhoneNumberResponseDto mapToResponse(PhoneNumberEntity phoneNumberEntity) {
        return PhoneNumberResponseDto.builder()
                .phoneNumber(phoneNumberEntity.getPhoneNumber())
                .build();
    }

}
