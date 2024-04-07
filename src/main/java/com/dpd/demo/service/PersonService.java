package com.dpd.demo.service;

import com.dpd.demo.error.ErrorCode;
import com.dpd.demo.exception.NonExistingPersonException;
import com.dpd.demo.persistence.entity.AddressEntity;
import com.dpd.demo.persistence.entity.PersonEntity;
import com.dpd.demo.persistence.entity.PhoneNumberEntity;
import com.dpd.demo.persistence.repository.PersonRepository;
import com.dpd.demo.service.mapper.AddressMapper;
import com.dpd.demo.service.mapper.PersonMapper;
import com.dpd.demo.service.mapper.PhoneNumberMapper;
import com.dpd.demo.web.dto.request.AddressRequestDto;
import com.dpd.demo.web.dto.request.PersonRequestDto;
import com.dpd.demo.web.dto.request.PhoneNumberRequestDto;
import com.dpd.demo.web.dto.response.PersonResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    private PersonMapper personMapper;
    private AddressMapper addressMapper;
    private PhoneNumberMapper phoneNumberMapper;

    @Transactional
    public PersonResponseDto addPerson(PersonRequestDto personRequestDto) {
        PersonEntity person = personMapper.mapToEntity(personRequestDto);

        person.setAddresses(new ArrayList<>());
        person.setPhoneNumbers(new ArrayList<>());

        for (AddressRequestDto addressDto : personRequestDto.getAddresses()) {
            AddressEntity addressEntity = addressMapper.mapToEntity(addressDto);
            addressEntity.setPerson(person);
            person.getAddresses().add(addressEntity);
        }

        for (PhoneNumberRequestDto phoneNumberDto : personRequestDto.getPhoneNumbers()) {
            PhoneNumberEntity phoneNumberEntity = phoneNumberMapper.mapToEntity(phoneNumberDto);
            phoneNumberEntity.setPerson(person);
            person.getPhoneNumbers().add(phoneNumberEntity);
        }

        person = personRepository.save(person);

        return personMapper.mapToResponse(person);
    }

    @Transactional(readOnly = true)
    public List<PersonResponseDto> getAllPersons() {
        List<PersonEntity> allPersons = personRepository.findAll();
        return allPersons.stream()
                .filter(person -> !person.isDeleted())
                .map(personMapper::mapToResponse)
                .toList();
    }

    @Transactional
    public PersonResponseDto updatePerson(Long id, PersonRequestDto personRequestDto) {
        Optional<PersonEntity> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw NonExistingPersonException.ofPersonId(ErrorCode.NON_EXISTING_PERSON, String.valueOf(id));
        }
        PersonEntity person = optionalPerson.get();

        personMapper.updateEntityFromDto(personRequestDto, person);

        person.getAddresses().clear();
        person.getPhoneNumbers().clear();

        for (AddressRequestDto addressDto : personRequestDto.getAddresses()) {
            AddressEntity addressEntity = addressMapper.mapToEntity(addressDto);
            addressEntity.setPerson(person);
            person.getAddresses().add(addressEntity);
        }

        for (PhoneNumberRequestDto phoneNumberDto : personRequestDto.getPhoneNumbers()) {
            PhoneNumberEntity phoneNumberEntity = phoneNumberMapper.mapToEntity(phoneNumberDto);
            phoneNumberEntity.setPerson(person);
            person.getPhoneNumbers().add(phoneNumberEntity);
        }

        person = personRepository.save(person);

        return personMapper.mapToResponse(person);
    }

    @Transactional
    public PersonResponseDto depersonalizePerson(Long id) {
        Optional<PersonEntity> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw NonExistingPersonException.ofPersonId(ErrorCode.NON_EXISTING_PERSON, String.valueOf(id));
        }
        PersonEntity person = optionalPerson.get();

        personMapper.unidentificationFields(person);

        person = personRepository.save(person);

        return personMapper.mapToResponse(person);
    }

    @Transactional
    public Long deletePersonById(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return id;
        } else {
            throw NonExistingPersonException.ofPersonId(ErrorCode.NON_EXISTING_PERSON, String.valueOf(id));
        }
    }

    public Long softDeletePersonById(Long id) {
        Optional<PersonEntity> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw NonExistingPersonException.ofPersonId(ErrorCode.NON_EXISTING_PERSON, String.valueOf(id));
        }
        PersonEntity person = optionalPerson.get();
        person.setDeleted(true);
        personRepository.save(person);
        return id;
    }

}
