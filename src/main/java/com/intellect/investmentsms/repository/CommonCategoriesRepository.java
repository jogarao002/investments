package com.intellect.investmentsms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.CommonCategories;

/**
 * Spring Data  repository for the CommonCategories entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommonCategoriesRepository extends JpaRepository<CommonCategories, Long> {

	CommonCategories findByName(String name);

}
