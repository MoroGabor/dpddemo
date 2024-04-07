package com.dpd.demo.unitTest.service;

import com.dpd.demo.exception.NonExistingPersonException;
import com.dpd.demo.persistence.entity.PersonEntity;
import com.dpd.demo.persistence.repository.PersonRepository;
import com.dpd.demo.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceDeleteTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testDeletePersonById_Success() {
        // GIVEN
        Long id = 1L;
        when(personRepository.existsById(id)).thenReturn(true);

        // WHEN
        Long deletedId = personService.deletePersonById(id);

        // THEN
        assertEquals(id, deletedId);
        verify(personRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeletePersonById_NonExistingPerson() {
        // GIVEN
        Long id = 1L;
        when(personRepository.existsById(id)).thenReturn(false);

        // WHEN & THEN
        assertThrows(NonExistingPersonException.class, () -> personService.deletePersonById(id));
        verify(personRepository, never()).deleteById(id);
    }

    @Test
    void testSoftDeletePersonById_Success() {
        // GIVEN
        Long id = 1L;
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(id);
        when(personRepository.findById(id)).thenReturn(Optional.of(personEntity));

        // WHEN
        Long deletedId = personService.softDeletePersonById(id);

        // THEN
        assertEquals(id, deletedId);
        assertTrue(personEntity.isDeleted());
        verify(personRepository, times(1)).save(personEntity);
    }

    @Test
    void testSoftDeletePersonById_NonExistingPerson() {
        // GIVEN
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(NonExistingPersonException.class, () -> personService.softDeletePersonById(id));
        verify(personRepository, never()).save(any());
    }

}
