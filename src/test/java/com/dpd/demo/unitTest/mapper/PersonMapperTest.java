package com.dpd.demo.unitTest.mapper;

import com.dpd.demo.persistence.entity.PersonEntity;
import com.dpd.demo.service.mapper.AddressMapper;
import com.dpd.demo.service.mapper.PersonMapper;
import com.dpd.demo.service.mapper.PhoneNumberMapper;
import com.dpd.demo.web.dto.request.AddressRequestDto;
import com.dpd.demo.web.dto.request.PersonRequestDto;
import com.dpd.demo.web.dto.request.PhoneNumberRequestDto;
import com.dpd.demo.web.dto.response.PersonResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    private final PersonMapper underTest = new PersonMapper(new AddressMapper(), new PhoneNumberMapper());

    @Test
    void mapToEntity() {
        // GIVEN
        PersonRequestDto personRequestDto = getPersonRequestDto();

        // WHEN
        PersonEntity actual = underTest.mapToEntity(personRequestDto);

        // THEN
        assertAll(
                () -> assertEquals(personRequestDto.getName(), actual.getName()),
                () -> assertEquals(personRequestDto.getBirthPlace(), actual.getBirthPlace()),
                () -> assertEquals(personRequestDto.getBirthDate(), actual.getBirthDate()),
                () -> assertEquals(personRequestDto.getMotherName(), actual.getMotherName()),
                () -> assertEquals(personRequestDto.getSocialSecurityNumber(), actual.getSocialSecurityNumber()),
                () -> assertEquals(personRequestDto.getTaxNumber(), actual.getTaxNumber()),
                () -> assertEquals(personRequestDto.getEmail(), actual.getEmail()),
                () -> assertEquals(personRequestDto.getAddresses().size(), actual.getAddresses().size()),
                () -> assertEquals(personRequestDto.getPhoneNumbers().size(), actual.getPhoneNumbers().size())
        );
    }

    @Test
    void mapToEntityNull() {
        // GIVEN
        PersonRequestDto personRequestDto = null;

        // WHEN
        Executable callMap = () -> underTest.mapToEntity(personRequestDto);

        // THEN
        NullPointerException exception = assertThrows(NullPointerException.class, callMap);
        assertNotNull(exception.getMessage());
    }

    @Test
    void mapToResponse() {
        // GIVEN
        PersonEntity personEntity = getPersonEntity();

        // WHEN
        PersonResponseDto actual = underTest.mapToResponse(personEntity);

        // THEN
        assertAll(
                () -> assertEquals(personEntity.getName(), actual.getName()),
                () -> assertEquals(personEntity.getBirthPlace(), actual.getBirthPlace()),
                () -> assertEquals(personEntity.getBirthDate(), actual.getBirthDate()),
                () -> assertEquals(personEntity.getMotherName(), actual.getMotherName()),
                () -> assertEquals(personEntity.getSocialSecurityNumber(), actual.getSocialSecurityNumber()),
                () -> assertEquals(personEntity.getTaxNumber(), actual.getTaxNumber()),
                () -> assertEquals(personEntity.getEmail(), actual.getEmail()),
                () -> assertEquals(personEntity.getAddresses().size(), actual.getAddresses().size()),
                () -> assertEquals(personEntity.getPhoneNumbers().size(), actual.getPhoneNumbers().size())
        );
    }

    @Test
    void updateEntityFromDto() {
        // GIVEN
        PersonRequestDto personRequestDto = getPersonRequestDto();
        PersonEntity personEntity = new PersonEntity();

        // WHEN
        underTest.updateEntityFromDto(personRequestDto, personEntity);

        // THEN
        assertAll(
                () -> assertEquals(personRequestDto.getName(), personEntity.getName()),
                () -> assertEquals(personRequestDto.getBirthPlace(), personEntity.getBirthPlace()),
                () -> assertEquals(personRequestDto.getBirthDate(), personEntity.getBirthDate()),
                () -> assertEquals(personRequestDto.getMotherName(), personEntity.getMotherName()),
                () -> assertEquals(personRequestDto.getSocialSecurityNumber(), personEntity.getSocialSecurityNumber()),
                () -> assertEquals(personRequestDto.getTaxNumber(), personEntity.getTaxNumber()),
                () -> assertEquals(personRequestDto.getEmail(), personEntity.getEmail()),
                () -> assertEquals(personRequestDto.getAddresses().size(), personEntity.getAddresses().size()),
                () -> assertEquals(personRequestDto.getPhoneNumbers().size(), personEntity.getPhoneNumbers().size())
        );
    }

    private static PersonRequestDto getPersonRequestDto() {
        List<AddressRequestDto> addresses = List.of(
                new AddressRequestDto(2123, "TestCity", "TestStreet", "123"),
                new AddressRequestDto(5432, "OtherCity", "OtherStreet", "456")
        );

        List<PhoneNumberRequestDto> phoneNumbers = List.of(
                new PhoneNumberRequestDto("123456789"),
                new PhoneNumberRequestDto("987654321")
        );

        return new PersonRequestDto(
                "TestName",
                "TestBirthPlace",
                LocalDate.of(2000, 1, 1),
                "TestMotherName",
                "1234567890",
                "0987654321",
                "test@example.com",
                addresses,
                phoneNumbers
        );
    }

    private static PersonEntity getPersonEntity() {
        List<AddressRequestDto> addresses = List.of(
                new AddressRequestDto(2123, "TestCity", "TestStreet", "123"),
                new AddressRequestDto(5432, "OtherCity", "OtherStreet", "456")
        );

        List<PhoneNumberRequestDto> phoneNumbers = List.of(
                new PhoneNumberRequestDto("123456789"),
                new PhoneNumberRequestDto("987654321")
        );

        PersonEntity personEntity = getPersonEntity(addresses, phoneNumbers);

        if (personEntity.getId() == null) {
            personEntity.setId(1L);
        }

        return personEntity;
    }

    private static PersonEntity getPersonEntity(List<AddressRequestDto> addresses, List<PhoneNumberRequestDto> phoneNumbers) {
        PersonMapper personMapper = new PersonMapper(new AddressMapper(), new PhoneNumberMapper());
        PersonRequestDto personRequestDto = new PersonRequestDto(
                "TestName",
                "TestBirthPlace",
                LocalDate.of(2000, 1, 1),
                "TestMotherName",
                "1234567890",
                "0987654321",
                "test@example.com",
                addresses,
                phoneNumbers
        );

        return personMapper.mapToEntity(personRequestDto);
    }


    @Test
    void identificationFieldsTest() {
        // GIVEN
        PersonEntity personEntity = getPersonEntity();

        // WHEN
        underTest.unidentificationFields(personEntity);

        // THEN
        assertTrue(expectedDepersonalizedName(personEntity.getId()).startsWith("1_"), personEntity.getName());
        assertEquals(LocalDate.now(), personEntity.getBirthDate());
        assertTrue(expectedDepersonalizedString(personEntity.getId()).startsWith("1_"), personEntity.getSocialSecurityNumber());
        assertTrue(expectedDepersonalizedString(personEntity.getId()).startsWith("1_"), personEntity.getTaxNumber());
        assertTrue(expectedDepersonalizedString(personEntity.getId()).startsWith("1_"), personEntity.getEmail());
    }

    private String expectedDepersonalizedName(long id) {
        return id + "_" + (int) (Math.random() * 10000);
    }

    private String expectedDepersonalizedString(long id) {
        return id + "_" + (int) (Math.random() * 10000);
    }

}
