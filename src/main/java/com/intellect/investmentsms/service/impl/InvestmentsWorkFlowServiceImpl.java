package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InvestmentsWorkFlow;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InvestmentsWorkFlowRepository;
import com.intellect.investmentsms.service.InvestmentsWorkFlowService;
import com.intellect.investmentsms.service.dto.InvestmentsWorkFlowDTO;
import com.intellect.investmentsms.service.mapper.InvestmentsWorkFlowMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

@Service
@Transactional
public class InvestmentsWorkFlowServiceImpl implements InvestmentsWorkFlowService {

	private final InvestmentsWorkFlowRepository investmentsWorkFlowRepository;

	private final InvestmentsWorkFlowMapper investmentsWorkFlowMapper;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	public InvestmentsWorkFlowServiceImpl(InvestmentsWorkFlowRepository investmentsWorkFlowRepository,
			InvestmentsWorkFlowMapper investmentsWorkFlowMapper) {
		this.investmentsWorkFlowRepository = investmentsWorkFlowRepository;
		this.investmentsWorkFlowMapper = investmentsWorkFlowMapper;
	}

	/**
	 *@implNote: Save the InvestmentsWorkFlow by "investmentsWorkFlowDTO".
	 * @param investmentsWorkFlowDTO. 
	 * @return the DTO Object InvestmentsWorkFlowDTO.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	public InvestmentsWorkFlowDTO save(InvestmentsWorkFlowDTO investmentsWorkFlowDTO) {
		if (investmentsWorkFlowDTO != null) {
			if (investmentsWorkFlowDTO.getId() != null) {
				InvestmentsWorkFlow investmentsWorkFlow = null;
				Optional<InvestmentsWorkFlow> investmentsWorkFlowOptional = investmentsWorkFlowRepository
						.findById(investmentsWorkFlowDTO.getId());
				if (investmentsWorkFlowOptional.isPresent()) {
					investmentsWorkFlow = investmentsWorkFlowOptional.get();
					investmentsWorkFlowDTO.setCreatedOn(investmentsWorkFlow.getCreatedOn());
					investmentsWorkFlowDTO.setCreatedBy(investmentsWorkFlow.getCreatedBy());
				}
			}
		}
		InvestmentsWorkFlow investmentsWorkFlows = investmentsWorkFlowMapper.toEntity(investmentsWorkFlowDTO);
		investmentsWorkFlows = investmentsWorkFlowRepository.save(investmentsWorkFlows);
		return investmentsWorkFlowMapper.toDto(investmentsWorkFlows);
	}

	/**
	 *@implNote: Get all the InvestmentsWorkFlow.
	 * @param. 
	 * @return the List List<InvestmentsWorkFlowDTO>.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	@Transactional(readOnly = true)
	public List<InvestmentsWorkFlowDTO> findAll() {
		LinkedList<InvestmentsWorkFlowDTO> investmentsWorkFlowDTOList = investmentsWorkFlowRepository.findAll().stream()
				.map(investmentsWorkFlowMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		if (investmentsWorkFlowDTOList != null && !investmentsWorkFlowDTOList.isEmpty()) {
			for (InvestmentsWorkFlowDTO investmentsWorkFlowDTO : investmentsWorkFlowDTOList) {
				if (null != investmentsWorkFlowDTO) {
					if (null != investmentsWorkFlowDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(investmentsWorkFlowDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							investmentsWorkFlowDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
				}
			}
		}
		return investmentsWorkFlowDTOList;
	}

	/**
	 *@implNote: Get the InvestmentsWorkFlow by "id".
	 * @param id. 
	 * @return the DTO Object InvestmentsWorkFlowDTO.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	public InvestmentsWorkFlowDTO findOne(Long id) {
		InvestmentsWorkFlowDTO investmentsWorkFlowDTO = null;
		Optional<InvestmentsWorkFlow> optInvestmentsWorkFlow = investmentsWorkFlowRepository.findById(id);
		if (optInvestmentsWorkFlow.isPresent()) {
			investmentsWorkFlowDTO = investmentsWorkFlowMapper.toDto(optInvestmentsWorkFlow.get());
			if (null != investmentsWorkFlowDTO) {
				if (null != investmentsWorkFlowDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(investmentsWorkFlowDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						investmentsWorkFlowDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
			}
		}
		return investmentsWorkFlowDTO;
	}

	/**
	 *@implNote: Delete the InvestmentsWorkFlow by "id".
	 * @param id. 
	 * @return void.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	public void delete(Long id) {
		InvestmentsWorkFlow investmentsWorkFlow = null;
		Optional<InvestmentsWorkFlow> investmentsWorkFlowOptional = investmentsWorkFlowRepository.findById(id);
		if (investmentsWorkFlowOptional.isPresent()) {
			investmentsWorkFlow = investmentsWorkFlowOptional.get();
			investmentsWorkFlow.setStatus(ApplicationConstants.IN_ACTIVE);
			investmentsWorkFlowRepository.save(investmentsWorkFlow);
		}
	}

}
