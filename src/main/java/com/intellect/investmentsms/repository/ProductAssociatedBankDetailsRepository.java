package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.ProductAssociatedBankDetails;

/**
 * Spring Data JPA repository for the ProductAssociatedBankDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductAssociatedBankDetailsRepository extends JpaRepository<ProductAssociatedBankDetails, Long> {

	/**
	 *@implNote: Repository call for ProductAssociatedBankDetails by "productid".
	 * @param productid . 
	 * @return the List List<ProductAssociatedBankDetailsDTO>.
	 * @author Dilip Kumar.G
	 */
	
	List<ProductAssociatedBankDetails> findByProductId(Long productid);}
