package com.intellect.investmentsms.repository;

import com.intellect.investmentsms.domain.InvestedBankDetails;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InvestedBankDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestedBankDetailsRepository extends JpaRepository<InvestedBankDetails, Long> {

	/**
	 *@implNote: get the invested bank Details  based on"bankName"
	 * @param bankName
	 * @return List<InvestedBankDetails>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<InvestedBankDetails> findByBankName(String bankName);

}
