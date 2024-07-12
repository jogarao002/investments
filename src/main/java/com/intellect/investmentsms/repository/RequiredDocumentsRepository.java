package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.RequiredDocuments;

/**
 * Spring Data JPA repository for the RequiredDocuments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequiredDocumentsRepository extends JpaRepository<RequiredDocuments, Long> {

	/**
   	 *@implNote: get all the RequiredDocuments based on productId.
   	 * @param productId.
   	 * @return List<RequiredDocuments>.
   	 * @author Dileep_Kumar.Gedela.
   	 */
	List<RequiredDocuments> findByProductId(Long productId);

	/**
   	 *@implNote: get all the RequiredDocuments based on productId and documentTypeId.
   	 * @param productId,documentTypeId.
   	 * @return List<RequiredDocuments>.
   	 * @author Dileep_Kumar.Gedela.
   	 */
	List<RequiredDocuments> findByProductIdAndDocumentTypeId(Long productId, Long documentTypeId);

	/**
   	 *@implNote: get all the RequiredDocuments based on productId, effectiveStartDate, status .
   	 * @param productId,effectiveStartDate, status.
   	 * @return List<RequiredDocuments>.
   	 * @author Dileep_Kumar.Gedela.
   	 */
	List<RequiredDocuments> findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(
			Long productId, Long effectiveStartDate, Integer status);

	/**
   	 *@implNote: get all the RequiredDocuments based on productId, effectiveStartDate, effectiveEndDate status .
   	 * @param productId, effectiveStartDate, effectiveEndDate, status.
   	 * @return List<RequiredDocuments>.
   	 * @author Dileep_Kumar.Gedela.
   	 */
	List<RequiredDocuments> findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
			Long productId, Long effectiveStartDate, Long effectiveEndDate, Integer status);

}
