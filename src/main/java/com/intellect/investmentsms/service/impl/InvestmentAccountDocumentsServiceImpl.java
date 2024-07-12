package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InvestmentAccountDocuments;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InvestmentAccountDocumentsRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.service.InvestmentAccountDocumentsService;
import com.intellect.investmentsms.service.dto.InvestmentAccountDocumentsDTO;
import com.intellect.investmentsms.service.dto.ProductDTO;
import com.intellect.investmentsms.service.mapper.InvestmentAccountDocumentsMapper;
import com.intellect.investmentsms.service.mapper.ProductMapper;

/**
 * Service Implementation for managing
 * {@link com.intellect.investmentsms.domain.InvestmentAccountDocuments}.
 */
@Service
@Transactional
public class InvestmentAccountDocumentsServiceImpl implements InvestmentAccountDocumentsService {

	private final InvestmentAccountDocumentsRepository investmentAccountDocumentsRepository;

	private final InvestmentAccountDocumentsMapper investmentAccountDocumentsMapper;

	private final ProductRepository productRepository;

	private final ProductMapper productMapper;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	public InvestmentAccountDocumentsServiceImpl(
			InvestmentAccountDocumentsRepository investmentAccountDocumentsRepository,
			InvestmentAccountDocumentsMapper investmentAccountDocumentsMapper, ProductRepository productRepository,
			ProductMapper productMapper) {
		this.investmentAccountDocumentsRepository = investmentAccountDocumentsRepository;
		this.investmentAccountDocumentsMapper = investmentAccountDocumentsMapper;
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	/**
	 * @implNote: save the investmentAccountDocumentsDTO.
	 * @param investmentAccountDocumentsDTO.
	 * @return investmentAccountDocumentsDTO.
	 * @author M.Bhavana
	 */
	@Override
	public InvestmentAccountDocumentsDTO save(InvestmentAccountDocumentsDTO investmentAccountDocumentsDTOList) {
		if (investmentAccountDocumentsDTOList.getId() != null) {
			Optional<InvestmentAccountDocuments> investmentAccountDocuments = investmentAccountDocumentsRepository
					.findById(investmentAccountDocumentsDTOList.getId());
			if (investmentAccountDocuments.isPresent()) {
				if (investmentAccountDocuments.get().getCreatedOn() != null) {
					investmentAccountDocumentsDTOList.setCreatedOn(investmentAccountDocuments.get().getCreatedOn());
				}
				if (investmentAccountDocuments.get().getCreatedBy() != null) {
					investmentAccountDocumentsDTOList.setCreatedBy(investmentAccountDocuments.get().getCreatedBy());
				}
			}
		}
		InvestmentAccountDocuments investmentAccountDocuments = investmentAccountDocumentsMapper
				.toEntity(investmentAccountDocumentsDTOList);
		investmentAccountDocuments = investmentAccountDocumentsRepository.save(investmentAccountDocuments);
		return investmentAccountDocumentsMapper.toDto(investmentAccountDocuments);
	}

	/**
	 * @implNote: get all investmentAccountDocumentsDTO.
	 * @return the List<InvestmentAccountDocumentsDTO>.
	 * @author M.Bhavana
	 */
	@Override
	@Transactional(readOnly = true)
	public List<InvestmentAccountDocumentsDTO> findAll() {
		List<InvestmentAccountDocumentsDTO> investmentAccountDocumentsDTOList = investmentAccountDocumentsRepository
				.findAll().stream().map(investmentAccountDocumentsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));

		if (investmentAccountDocumentsDTOList != null && !investmentAccountDocumentsDTOList.isEmpty()) {
			for (InvestmentAccountDocumentsDTO investmentAccountDocumentsDTO : investmentAccountDocumentsDTOList) {
				if (null != investmentAccountDocumentsDTO) {
					if (null != investmentAccountDocumentsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(investmentAccountDocumentsDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							investmentAccountDocumentsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != investmentAccountDocumentsDTO.getProductId()) {
						Optional<Product> optProduct = productRepository
								.findById(investmentAccountDocumentsDTO.getProductId());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							investmentAccountDocumentsDTO.setProductName(optProduct.get().getName());
						}
					}

				}
			}
		}
		return investmentAccountDocumentsDTOList;
	}

	/**
	 * @implNote: get investmentAccountDocumentsDTO by id.
	 * @param id.
	 * @return investmentAccountDocumentsDTO.
	 * @author M.Bhavana
	 */
	@Override
	@Transactional(readOnly = true)
	public InvestmentAccountDocumentsDTO findOne(Long id) {
		InvestmentAccountDocumentsDTO investmentAccountDocumentsDTO = null;
		Optional<InvestmentAccountDocumentsDTO> optInvestmentAccountDocumentsDTO = investmentAccountDocumentsRepository
				.findById(id).map(investmentAccountDocumentsMapper::toDto);
		if (optInvestmentAccountDocumentsDTO.isPresent()) {
			investmentAccountDocumentsDTO = optInvestmentAccountDocumentsDTO.get();
			if (null != investmentAccountDocumentsDTO) {
				if (null != investmentAccountDocumentsDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(investmentAccountDocumentsDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						investmentAccountDocumentsDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
				if (null != investmentAccountDocumentsDTO.getProductId()) {
					Optional<Product> optProduct = productRepository
							.findById(investmentAccountDocumentsDTO.getProductId());
					if (optProduct.isPresent() && null != optProduct.get().getName()) {
						investmentAccountDocumentsDTO.setProductName(optProduct.get().getName());
					}
				}
			}
		}
		return investmentAccountDocumentsDTO;
	}

	
	/**
	 * @implNote: delete investmentAccountDocumentsDTO by id.
	 * @param id.
	 * @return void.
	 * @author M.Bhavana
	 */
	@Override
	public void delete(Long id) {

		investmentAccountDocumentsRepository.deleteById(id);
	}

	/**
	 * @implNote: get the list of investmentAccountDocumentsDTO by productId.
	 * @param productId.
	 * @return the List<InvestmentAccountDocumentsDTO>.
	 * @author M.Bhavana
	 */
	@Override
	@Transactional(readOnly = true)
	public List<InvestmentAccountDocumentsDTO> getAllByProductId(Long id) {
		List<InvestmentAccountDocuments> investmentAccountDocumentsList = investmentAccountDocumentsRepository
				.findAllByProductId(id);
		List<InvestmentAccountDocumentsDTO> investmentAccountDocumentsDTOList = null;
		if (investmentAccountDocumentsList != null && !investmentAccountDocumentsList.isEmpty()) {
			investmentAccountDocumentsDTOList = investmentAccountDocumentsMapper.toDto(investmentAccountDocumentsList);
			if (investmentAccountDocumentsDTOList != null && !investmentAccountDocumentsDTOList.isEmpty()) {
				for (InvestmentAccountDocumentsDTO investmentAccountDocumentsDTO : investmentAccountDocumentsDTOList) {
					if (investmentAccountDocumentsDTO != null && investmentAccountDocumentsDTO.getProductId() != null) {
						Optional<Product> product = productRepository
								.findById(investmentAccountDocumentsDTO.getProductId());
						if (product.isPresent()) {
							ProductDTO productDTO = productMapper.toDto(product.get());
							if (productDTO != null && productDTO.getName() != null)
								investmentAccountDocumentsDTO.setProductName(productDTO.getName());
					
						}
						if (null != investmentAccountDocumentsDTO.getStatus()) {
							Optional<CommonStatus> optCommonStatus = commonStatusRepository
									.findById(investmentAccountDocumentsDTO.getStatus().longValue());
							if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
								investmentAccountDocumentsDTO.setStatusName(optCommonStatus.get().getName());
							}
						}
					}
				}
			}
		}
		return investmentAccountDocumentsDTOList;
	}
	/**
	 * @implNote: get the list of InvestmentAccountDocumentsDTO by termAccountId.
	 * @param termAccountId.
	 * @return the List<InvestmentAccountDocumentsDTO>.
	 * @author M.Bhavana
	 */
	@Override
	@Transactional(readOnly = true)
	public List<InvestmentAccountDocumentsDTO> getAllByTermAccountId(Long id) {
		List<InvestmentAccountDocuments> investmentAccountDocumentsList = investmentAccountDocumentsRepository
				.findAllByTermAccId(id);
		List<InvestmentAccountDocumentsDTO> investmentAccountDocumentsDTOList = null;
		if (investmentAccountDocumentsList != null && !investmentAccountDocumentsList.isEmpty()) {
			investmentAccountDocumentsDTOList = investmentAccountDocumentsMapper.toDto(investmentAccountDocumentsList);
			if (investmentAccountDocumentsDTOList != null && !investmentAccountDocumentsDTOList.isEmpty()) {
				for (InvestmentAccountDocumentsDTO investmentAccountDocumentsDTO : investmentAccountDocumentsDTOList) {
					if (investmentAccountDocumentsDTO != null && investmentAccountDocumentsDTO.getProductId() != null) {
						Optional<Product> product = productRepository
								.findById(investmentAccountDocumentsDTO.getProductId());
						if (product.isPresent()) {
							ProductDTO productDTO = productMapper.toDto(product.get());
							if (productDTO != null && productDTO.getName() != null)
								investmentAccountDocumentsDTO.setProductName(productDTO.getName());
					
						}
						if (null != investmentAccountDocumentsDTO.getStatus()) {
							Optional<CommonStatus> optCommonStatus = commonStatusRepository
									.findById(investmentAccountDocumentsDTO.getStatus().longValue());
							if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
								investmentAccountDocumentsDTO.setStatusName(optCommonStatus.get().getName());
							}
						}
					}
				}
			}
		}
		return investmentAccountDocumentsDTOList;
	}
}
