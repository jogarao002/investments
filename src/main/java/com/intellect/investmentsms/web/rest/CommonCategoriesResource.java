package com.intellect.investmentsms.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.CommonCategoriesService;
import com.intellect.investmentsms.service.dto.CommonCategoriesDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing
 * {@link com.intellect.appraisal.domain.CommonCategories}.
 */
@RestController
@RequestMapping("/common_categories")
public class CommonCategoriesResource {

	private final CommonCategoriesService commonCategoriesService;

	public CommonCategoriesResource(CommonCategoriesService commonCategoriesService) {
		this.commonCategoriesService = commonCategoriesService;
	}

	/**
	 * {@code POST  /common-categories} : Create a new commonCategories.
	 *
	 * @param commonCategoriesDTO the commonCategoriesDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new commonCategoriesDTO, or with status
	 *         {@code 400 (Bad Request)} if the commonCategories has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/add")
	public ResponseObject createCommonCategories(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody CommonCategoriesDTO commonCategoriesDTO) throws URISyntaxException {
		List<CommonCategoriesDTO> data = null;
		CommonCategoriesDTO result = null;
		try {
			if (commonCategoriesDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = commonCategoriesService.save(commonCategoriesDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code PUT  /common-categories} : Updates an existing commonCategories.
	 *
	 * @param commonCategoriesDTO the commonCategoriesDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated commonCategoriesDTO, or with status
	 *         {@code 400 (Bad Request)} if the commonCategoriesDTO is not valid, or
	 *         with status {@code 500 (Internal Server Error)} if the
	 *         commonCategoriesDTO couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/update")
	public ResponseObject updateCommonCategories(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody CommonCategoriesDTO commonCategoriesDTO) throws URISyntaxException {
		List<CommonCategoriesDTO> data = null;
		CommonCategoriesDTO result = null;
		try {
			if (commonCategoriesDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = commonCategoriesService.save(commonCategoriesDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.UPDATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code GET  /common-categories} : get all the commonCategories.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of commonCategories in body.
	 */
	@GetMapping("/get_all")
	public ResponseObject getAllCommonCategories(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {

		List<CommonCategoriesDTO> result = null;
		try {
			result = commonCategoriesService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}

	/**
	 * {@code GET  /common-categories/:id} : get the "id" commonCategories.
	 *
	 * @param id the id of the commonCategoriesDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the commonCategoriesDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/get")
	public ResponseObject getCommonCategory(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
		List<CommonCategoriesDTO> data = null;
		try {
			CommonCategoriesDTO result = commonCategoriesService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.FETCH_RECORD_FAILED + ", " + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
	 * {@code DELETE  /common-categories/:id} : delete the "id" commonCategories.
	 *
	 * @param id the id of the commonCategoriesDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/remove")
	public ResponseObject deleteCommonCategories(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) throws InvestmentsBusinessException {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			commonCategoriesService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.DELETE_RECORD_FAILED+ ", " + e.getMessage(), null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_CATEGORY + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}

}
