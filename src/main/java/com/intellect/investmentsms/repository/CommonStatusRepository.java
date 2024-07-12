package com.intellect.investmentsms.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.CommonStatus;

/**
 * Spring Data  repository for the CommonStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommonStatusRepository extends JpaRepository<CommonStatus, Long> {

	List<CommonStatus> findByCategoryIdAndStatus(Long id, Integer status);

	List<CommonStatus> findByNameAndCategoryId(String name, Long commonCategoryId);
	
	/**
	@implNote: getAll the response object by "commonCategoryId".
	@param commonCategoryId.
	@return List<CommonStatusDTO>.
	@author praveen
	*/
	List<CommonStatus> findByCategoryId(Long commonCategoryId);
	
	CommonStatus findByName(String name);
}
