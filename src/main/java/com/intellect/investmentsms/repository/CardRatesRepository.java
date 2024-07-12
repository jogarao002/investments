package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.CardRates;

/**
 * Spring Data JPA repository for the CardRates entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CardRatesRepository extends JpaRepository<CardRates, Long> {

	List<CardRates> findByPacsIdAndStatus(Long pacsId, Integer status);

	List<CardRates> findByPacsIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndStatus(
			Long pacsId, Long effectiveStartDate, Integer maxTenure, Integer maxTenure2, Integer status);

	CardRates findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateIsNullAndStatus(
			Long pacsId, Long depositDate, Integer tenureIndays, Integer tenureIndays2, Integer tenureType,
			Integer status);

	CardRates findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateGreaterThanEqualAndStatus(
			Long pacsId, Long depositDate, Integer tenureIndays, Integer tenureIndays2, Integer tenureType,
			Long depositDate2, Integer status);
}
