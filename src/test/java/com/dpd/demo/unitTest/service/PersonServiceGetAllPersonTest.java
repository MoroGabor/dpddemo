package com.dpd.demo.unitTest.service;

import com.dpd.demo.persistence.entity.PersonEntity;
import com.dpd.demo.persistence.repository.PersonRepository;
import com.dpd.demo.service.PersonService;
import com.dpd.demo.service.mapper.PersonMapper;
import com.dpd.demo.web.dto.response.PersonResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceGetAllPersonTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGetAllPersons() {
        // GIVEN
        List<PersonEntity> personEntities = new ArrayList<>();
        personEntities.add(createPersonEntity(1));
        personEntities.add(createPersonEntity(2));

        List<PersonResponseDto> expectedPersonResponseDtos = new ArrayList<>();
        expectedPersonResponseDtos.add(createPersonResponse(1));
        expectedPersonResponseDtos.add(createPersonResponse(2));

        when(personRepository.findAll()).thenReturn(personEntities);
        when(personMapper.mapToResponse(personEntities.get(0))).thenReturn(expectedPersonResponseDtos.get(0));
        when(personMapper.mapToResponse(personEntities.get(1))).thenReturn(expectedPersonResponseDtos.get(1));

        // WHEN
        List<PersonResponseDto> actualPersonResponseDtos = personService.getAllPersons();

        // THEN
        assertEquals(expectedPersonResponseDtos.size(), actualPersonResponseDtos.size());
    }

    private static PersonEntity createPersonEntity(int count) {
        return PersonEntity.builder().name("testName" + count)
                .birthPlace("testPlace" + count)
                .birthDate(LocalDate.now().minusDays(2))
                .motherName("testMname" + count)
                .socialSecurityNumber("123 456 456")
                .taxNumber("123456-2-08")
                .email("test@gmail.com")
                .build();
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
