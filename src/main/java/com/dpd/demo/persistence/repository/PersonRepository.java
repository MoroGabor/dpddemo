package com.dpd.demo.persistence.repository;

import com.dpd.demo.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository  extends JpaRepository<PersonEntity, Long> {
}
