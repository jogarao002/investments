package com.intellect.investmentsms.service.mapper;

import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {}
