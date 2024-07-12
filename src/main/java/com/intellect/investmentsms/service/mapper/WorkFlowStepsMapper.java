package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.WorkFlowSteps;
import com.intellect.investmentsms.service.dto.WorkFlowStepsDTO;

/**
 * Mapper for the entity {@link WorkFlowSteps} and its DTO
 * {@link WorkFlowStepsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkFlowStepsMapper extends EntityMapper<WorkFlowStepsDTO, WorkFlowSteps> {

}
