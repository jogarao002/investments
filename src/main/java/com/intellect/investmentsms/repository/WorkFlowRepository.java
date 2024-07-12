package com.intellect.investmentsms.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.WorkFlow;

/**
 * Spring Data  repository for the WorkFlow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkFlowRepository extends JpaRepository<WorkFlow, Long> {

	List<WorkFlow> findByCategoryIdAndIsExceptional(Long categoryId, Integer isExceptional);

}
