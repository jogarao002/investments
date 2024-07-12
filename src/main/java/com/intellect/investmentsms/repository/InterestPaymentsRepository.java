package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.InterestPayments;

/**
 * Spring Data JPA repository for the InterestPayments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InterestPaymentsRepository extends JpaRepository<InterestPayments, Long> {

	/**
     *@implNote: get the InterestPayments by id and timeInMillis.
     * @param id and timeInMillis.
     * @return the Object of InterestPayments
     * @author K.Ramu
     */
	InterestPayments findByTermAccIdAndInterestPostingDate(Long id, long timeInMillis);

	/**
     *@implNote: get the list of InterestPayments by products.
     * @param products.
     * @return the List<InterestPayments>.
     * @author K.Ramu
     */
	List<InterestPayments> findByProducts(Long products);

	/**
     *@implNote: get the list of InterestPayments by termAccId.
     * @param termAccId.
     * @return the List<InterestPayments>.
     * @author K.Ramu
     */
	List<InterestPayments> findByTermAccId(Long termAccId);
}
