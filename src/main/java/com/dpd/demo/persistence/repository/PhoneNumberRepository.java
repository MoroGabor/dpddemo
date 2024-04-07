package com.dpd.demo.persistence.repository;

import com.dpd.demo.persistence.entity.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Long> {
}
