package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.CommonCategories;
import com.intellect.investmentsms.service.dto.CommonCategoriesDTO;

/**
 * Mapper for the entity {@link CommonCategories} and its DTO
 * {@link CommonCategoriesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonCategoriesMapper extends EntityMapper<CommonCategoriesDTO, CommonCategories> {

}
