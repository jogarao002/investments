package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.PenaltyConfig;

/**
 * Spring Data JPA repository for the PenaltyConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PenaltyConfigRepository extends JpaRepository<PenaltyConfig, Long> {

	/**
	 * @implNote: get all the PenaltyConfigs based on productId.
	 * @param productId.
	 * @return List<PenaltyConfig>.
	 * @author Dileep_Kumar.Gedela.
	 */
	List<PenaltyConfig> findByProductId(Long productId);

	/**
	 * @implNote: get all the PenaltyConfigs based on
	 *            productId,effectiveStartDate,status.
	 * @param productId,effectiveStartDate, status.
	 * @return List<PenaltyConfig>.
	 * @author Dileep_Kumar.Gedela.
	 */
	List<PenaltyConfig> findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(
			Long productId, Long effectiveStartDate, Integer status);

	/**
	 * @implNote: get all the PenaltyConfigs based on
	 *            productId,effectiveStartDate,effectiveEndDate, status.
	 * @param productId,effectiveStartDate,effectiveEndDate, status.
	 * @return List<PenaltyConfig>.
	 * @author Dileep_Kumar.Gedela.
	 */
	List<PenaltyConfig> findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
			Long productId, Long effectiveStartDate, Long effectiveEndDate, Integer status);
}
