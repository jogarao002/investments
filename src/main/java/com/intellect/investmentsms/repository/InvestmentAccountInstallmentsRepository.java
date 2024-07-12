package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.InvestmentAccountInstallments;

/**
 * Spring Data JPA repository for the InvestmentAccountInstallments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestmentAccountInstallmentsRepository extends JpaRepository<InvestmentAccountInstallments, Long> {

	InvestmentAccountInstallments findByTermAccIdAndInstallmentDate(Long id, long timeInMillis);

	List<InvestmentAccountInstallments> findByTermAccId(Long termAccId);

	List<InvestmentAccountInstallments> findByTermAccIdAndStatus(Long id, Long id2);

}
