package com.intellect.investmentsms.service.mapper;

import com.intellect.investmentsms.domain.RequiredDocuments;
import com.intellect.investmentsms.service.dto.RequiredDocumentsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RequiredDocuments} and its DTO {@link RequiredDocumentsDTO}.
 */
@Mapper(componentModel = "spring")
public interface RequiredDocumentsMapper extends EntityMapper<RequiredDocumentsDTO, RequiredDocuments> {
	
}
