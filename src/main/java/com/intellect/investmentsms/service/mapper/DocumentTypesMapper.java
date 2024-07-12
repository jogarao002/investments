package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.DocumentTypes;
import com.intellect.investmentsms.service.dto.DocumentTypesDTO;

/**
 * Mapper for the entity {@link DocumentTypes} and its DTO {@link DocumentTypesDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentTypesMapper extends EntityMapper<DocumentTypesDTO, DocumentTypes> {
}
