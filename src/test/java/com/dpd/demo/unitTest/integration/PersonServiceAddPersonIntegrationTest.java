package com.dpd.demo.unitTest.integration;

import com.dpd.demo.persistence.entity.AddressEntity;
import com.dpd.demo.persistence.entity.PersonEntity;
import com.dpd.demo.persistence.entity.PhoneNumberEntity;
import com.dpd.demo.persistence.repository.PersonRepository;
import com.dpd.demo.service.PersonService;
import com.dpd.demo.service.mapper.AddressMapper;
import com.dpd.demo.service.mapper.PersonMapper;
import com.dpd.demo.service.mapper.PhoneNumberMapper;
import com.dpd.demo.web.dto.request.AddressRequestDto;
import com.dpd.demo.web.dto.request.PersonRequestDto;
import com.dpd.demo.web.dto.request.PhoneNumberRequestDto;
import com.dpd.demo.web.dto.response.PersonResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceAddPersonIntegrationTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private PhoneNumberMapper phoneNumberMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testAddPerson() {
        // Arrange
        PersonRequestDto personRequestDto = new PersonRequestDto();
        AddressRequestDto addressRequestDto = new AddressRequestDto();
        PhoneNumberRequestDto phoneNumberRequestDto = new PhoneNumberRequestDto();

        personRequestDto.setAddresses(List.of(addressRequestDto));
        personRequestDto.setPhoneNumbers(List.of(phoneNumberRequestDto));

        PersonEntity personEntity = new PersonEntity();
        PersonResponseDto expectedResponseDto = createPersonResponse(1);

        when(addressMapper.mapToEntity(addressRequestDto)).thenReturn(new AddressEntity());
        when(phoneNumberMapper.mapToEntity(phoneNumberRequestDto)).thenReturn(new PhoneNumberEntity());
        when(personMapper.mapToEntity(personRequestDto)).thenReturn(personEntity);
        when(personMapper.mapToResponse(personEntity)).thenReturn(expectedResponseDto);
        when(personRepository.save(any(PersonEntity.class))).thenReturn(personEntity);

        // Act
        PersonResponseDto actualResponseDto = personService.addPerson(personRequestDto);

        // Assert
        assertEquals(expectedResponseDto, actualResponseDto);
    }

    private static PersonResponseDto createPersonResponse(int count) {
        return PersonResponseDto.builder()
                .name("testName" + count)
                .birthPlace("testPlace" + count)
                .birthDate(LocalDate.now().minusDays(2))
                .motherName("testMname" + count)
                .socialSecurityNumber("123 456 456")
                .taxNumber("123456-2-08")
                .email("test@gmail.com")
                .build();
    }
}

