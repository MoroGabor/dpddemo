package com.dpd.demo.service.mapper;

import com.dpd.demo.persistence.entity.PersonEntity;
import com.dpd.demo.web.dto.request.AddressRequestDto;
import com.dpd.demo.web.dto.request.PersonRequestDto;
import com.dpd.demo.web.dto.request.PhoneNumberRequestDto;
import com.dpd.demo.web.dto.response.PersonResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class PersonMapper {

    private AddressMapper addressMapper;

    private PhoneNumberMapper phoneNumberMapper;

    public PersonEntity mapToEntity(PersonRequestDto personRequestDto) {
        return PersonEntity.builder()
                .name(personRequestDto.getName())
                .birthPlace(personRequestDto.getBirthPlace())
                .birthDate(personRequestDto.getBirthDate())
                .motherName(personRequestDto.getMotherName())
                .socialSecurityNumber(personRequestDto.getSocialSecurityNumber())
                .taxNumber(personRequestDto.getTaxNumber())
                .email(personRequestDto.getEmail())
                .addresses(personRequestDto.getAddresses().stream()
                        .map(addressMapper::mapToEntity)
                        .toList())
                .phoneNumbers(personRequestDto.getPhoneNumbers().stream()
                        .map(phoneNumberMapper::mapToEntity)
                        .toList())
                .build();
    }

    public PersonResponseDto mapToResponse(PersonEntity personEntity) {
        return PersonResponseDto.builder()
                .name(personEntity.getName())
                .birthPlace(personEntity.getBirthPlace())
                .birthDate(personEntity.getBirthDate())
                .motherName(personEntity.getMotherName())
                .socialSecurityNumber(personEntity.getSocialSecurityNumber())
                .taxNumber(personEntity.getTaxNumber())
                .email(personEntity.getEmail())
                .addresses(personEntity.getAddresses().stream()
                        .map(addressMapper::mapToResponse)
                        .toList())
                .phoneNumbers(personEntity.getPhoneNumbers().stream()
                        .map(phoneNumberMapper::mapToResponse)
                        .toList())
                .build();
    }

    public void updateEntityFromDto(PersonRequestDto personRequestDto, PersonEntity personEntity) {
        personEntity.setName(personRequestDto.getName());
        personEntity.setBirthPlace(personRequestDto.getBirthPlace());
        personEntity.setBirthDate(personRequestDto.getBirthDate());
        personEntity.setMotherName(personRequestDto.getMotherName());
        personEntity.setSocialSecurityNumber(personRequestDto.getSocialSecurityNumber());
        personEntity.setTaxNumber(personRequestDto.getTaxNumber());
        personEntity.setEmail(personRequestDto.getEmail());

        personEntity.getAddresses().clear();
        personEntity.getPhoneNumbers().clear();

        for (AddressRequestDto addressDto : personRequestDto.getAddresses()) {
            personEntity.getAddresses().add(addressMapper.mapToEntity(addressDto));
        }

        for (PhoneNumberRequestDto phoneNumberDto : personRequestDto.getPhoneNumbers()) {
            personEntity.getPhoneNumbers().add(phoneNumberMapper.mapToEntity(phoneNumberDto));
        }
    }

    public void unidentificationFields(PersonEntity person) {
        person.setName(depersonalizeWithId(person.getId()));
        person.setBirthDate(LocalDate.now());
        person.setSocialSecurityNumber(depersonalizeWithId(person.getId()));
        person.setTaxNumber(depersonalizeWithId(person.getId()));
        person.setEmail(depersonalizeWithId(person.getId()));
    }

    private String depersonalizeWithId(long id) {
        return id + "_" + (int) (Math.random() * 10000);
    }

}
