package com.intellect.investmentsms.service.mapper;

import com.intellect.investmentsms.domain.PenaltyConfig;
import com.intellect.investmentsms.service.dto.PenaltyConfigDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PenaltyConfig} and its DTO {@link PenaltyConfigDTO}.
 */
@Mapper(componentModel = "spring")
public interface PenaltyConfigMapper extends EntityMapper<PenaltyConfigDTO, PenaltyConfig> {
	
}
