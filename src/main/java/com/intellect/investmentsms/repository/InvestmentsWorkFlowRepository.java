package com.intellect.investmentsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.InvestmentsWorkFlow;

@SuppressWarnings("unused")
@Repository
public interface InvestmentsWorkFlowRepository extends JpaRepository<InvestmentsWorkFlow, Long> {}
