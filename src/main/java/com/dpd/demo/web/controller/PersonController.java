package com.dpd.demo.web.controller;

import com.dpd.demo.service.PersonService;
import com.dpd.demo.web.dto.request.PersonRequestDto;
import com.dpd.demo.web.dto.response.PersonResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/persons")
@Validated
public class PersonController {

    private PersonService personService;

    @PostMapping("/person")
    @Transactional
    public ResponseEntity<PersonResponseDto> addPerson(@Valid @RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok().body(personService.addPerson(personRequestDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonResponseDto>> getAllPersons() {
        List<PersonResponseDto> allPersons = personService.getAllPersons();
        return ResponseEntity.ok().body(allPersons);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<PersonResponseDto> updatePerson(@PathVariable Long id, @Valid @RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok().body(personService.updatePerson(id, personRequestDto));
    }

    @PatchMapping("/{id}/depersonalize")
    @Transactional
    public ResponseEntity<PersonResponseDto> depersonalizePerson(@PathVariable Long id) {
        return ResponseEntity.ok().body(personService.depersonalizePerson(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePersonById(@PathVariable Long id) {
        Long deletedPersonId = personService.deletePersonById(id);
        return ResponseEntity.ok().body(deletedPersonId);
    }

    @DeleteMapping("/{id}/soft-delete")
    public ResponseEntity<Long> softDeletePersonById(@PathVariable Long id) {
        Long deletedPersonId = personService.softDeletePersonById(id);
        return ResponseEntity.ok().body(deletedPersonId);
    }

}
