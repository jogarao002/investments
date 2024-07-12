package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.InterestPolicyConfig;

/**
 * Spring Data JPA repository for the IntrestPolicyConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InterestPolicyConfigRepository extends JpaRepository<InterestPolicyConfig, Long> {

	/**
   	 *@implNote: Repository call for effectiveStartDate, effectiveEndDate, isNull and Status. .
   	 * @param productId, depositDate, active. 
   	 * @return teh List List<InterestPolicyConfig>.
   	 * @author Dilip Kumar.G
   	 */
	
	List<InterestPolicyConfig> findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(
			Long productId, Long depositDate, Integer active);
	
	/**
   	 *@implNote: Repository call for effectiveStartDate, effectiveEndDate and Status. .
   	 * @param productId, depositDate, depositDate2, active. 
   	 * @return teh List List<InterestPolicyConfig>.
   	 * @author Dilip Kumar.G
   	 */

	List<InterestPolicyConfig> findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
			Long productId, Long depositDate, Long depositDate2, Integer active);

	InterestPolicyConfig findByProductIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateIsNullAndStatus(
			Long productId, Long depositDate, Integer tenureIndays, Integer tenureIndays2, Integer tenureType,
			Integer active);

	InterestPolicyConfig findByProductIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateGreaterThanEqualAndStatus(
			Long productId, Long depositDate, Integer tenureIndays, Integer tenureIndays2, Integer tenureType,
			Long depositDate2, Integer active);

	List<InterestPolicyConfig> findByProductId(Long productId);

}
