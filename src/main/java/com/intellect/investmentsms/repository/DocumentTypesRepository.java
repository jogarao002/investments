package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.DocumentTypes;

/**
 * Spring Data JPA repository for the DocumentTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentTypesRepository extends JpaRepository<DocumentTypes, Long> {

	List<DocumentTypes> findByName(String name);
}
