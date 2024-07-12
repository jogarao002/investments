package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.InvestmentsWorkFlow;
import com.intellect.investmentsms.service.dto.InvestmentsWorkFlowDTO;


@Mapper(componentModel = "spring")
public interface InvestmentsWorkFlowMapper extends EntityMapper<InvestmentsWorkFlowDTO, InvestmentsWorkFlow> {
}
