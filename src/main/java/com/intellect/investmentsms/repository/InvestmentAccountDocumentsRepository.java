package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.InvestmentAccountDocuments;

/**
 * Spring Data JPA repository for the InvestmentAccountDocuments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestmentAccountDocumentsRepository extends JpaRepository<InvestmentAccountDocuments, Long> {
	/**
	 * @implNote: get the list of investmentAccountDocuments by productId.
	 * @param productId.
	 * @return the List<InvestmentAccountDocuments>.
	 * @author M.Bhavana
	 */
	List<InvestmentAccountDocuments> findAllByProductId(Long productId);
	/**
	 * @implNote: get the list of investmentAccountDocuments by termAccountId.
	 * @param termAccountId.
	 * @return the List<InvestmentAccountDocuments>.
	 * @author M.Bhavana
	 */
	List<InvestmentAccountDocuments> findAllByTermAccId(Long termAccountId);
}
