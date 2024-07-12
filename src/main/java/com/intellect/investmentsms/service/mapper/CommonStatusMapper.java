package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.service.dto.CommonStatusDTO;

/**
 * Mapper for the entity {@link CommonStatus} and its DTO
 * {@link CommonStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonStatusMapper extends EntityMapper<CommonStatusDTO, CommonStatus> {
}
