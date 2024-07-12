package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.InterestPolicyConfig;
import com.intellect.investmentsms.service.dto.InterestPolicyConfigDTO;

/**
 * Mapper for the entity {@link IntrestPolicyConfig} and its DTO {@link IntrestPolicyConfigDTO}.
 */
@Mapper(componentModel = "spring")
public interface InterestPolicyConfigMapper extends EntityMapper<InterestPolicyConfigDTO, InterestPolicyConfig> {}
