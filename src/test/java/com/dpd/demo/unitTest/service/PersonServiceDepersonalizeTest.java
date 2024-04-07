package com.dpd.demo.unitTest.service;

import com.dpd.demo.exception.NonExistingPersonException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceDepersonalizeTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testDepersonalizePerson_Success() {
        // GIVEN
        Long id = 1L;
        PersonEntity personEntity = createPersonEntity(1);
        personEntity.setId(id);

        PersonResponseDto responseDto = createPersonResponse(1);
        when(personMapper.mapToResponse(any())).thenReturn(responseDto);

        when(personRepository.findById(id)).thenReturn(Optional.of(personEntity));

        // WHEN
        PersonResponseDto result = personService.depersonalizePerson(id);

        // THEN
        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(personRepository, times(1)).findById(id);
        verify(personRepository, times(1)).save(personEntity);
        verify(personMapper, times(1)).mapToResponse(any());
        verify(personMapper, times(1)).unidentificationFields(personEntity);
    }

    @Test
    void testDepersonalizePerson_NonExistingPerson() {
        // GIVEN
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(NonExistingPersonException.class, () -> personService.depersonalizePerson(id));
        verify(personRepository, times(1)).findById(id);
        verify(personMapper, never()).mapToResponse(any());
        verify(personMapper, never()).unidentificationFields(any());
        verify(personRepository, never()).save(any());
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

}
