package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.WorkFlow;
import com.intellect.investmentsms.service.dto.WorkFlowDTO;

/**
 * Mapper for the entity {@link WorkFlow} and its DTO {@link WorkFlowDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkFlowMapper extends EntityMapper<WorkFlowDTO, WorkFlow> {

}
