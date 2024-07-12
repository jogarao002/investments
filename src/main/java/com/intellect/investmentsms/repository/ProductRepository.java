package com.intellect.investmentsms.repository;

import com.intellect.investmentsms.domain.Product;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 *@implNote: get the product Details  based on"effective start date,effective end date and status"
	 * @param effectiveStartDate,effectiveEndDate,status
	 * @return List<Product> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<Product> findByEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
			Long effectiveStartDate, Long effectiveEndDate, Integer status);
	/**
	 *@implNote: get the product Details  based on"effective start date and status"
	 * @param effectiveStartDate,status
	 * @return List<Product> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<Product> findByEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(Long effectiveStartDate,
			Integer status);
	/**
	 *@implNote: get the product Details  based on"effective start date,effective end date "
	 * @param effectiveStartDate,effectiveEndDate
	 * @return List<Product> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<Product> findByEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndPacsId(Long currentDateWithoutTime,
			Long currentDateWithoutTime2,Long pacsId);
	/**
	 *@implNote: get the product Details  based on"effective start date,effective end date "
	 * @param effectiveStartDate,effectiveEndDate
	 * @return List<Product> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<Product> findByEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndPacsId(Long currentDateWithoutTime, Long pacsId);
	
}
