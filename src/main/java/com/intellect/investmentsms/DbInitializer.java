package com.intellect.investmentsms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.intellect.investmentsms.domain.CommonCategories;
import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.repository.CommonCategoriesRepository;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.service.dto.DataSeedingEnum;
import com.intellect.investmentsms.util.ApplicationConstants;

@Component
public class DbInitializer implements CommandLineRunner {

	@Autowired
	private CommonCategoriesRepository commonCategoriesRepository;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	@Override
	public void run(String... args) throws Exception {
		commonCategories();
		commonStatus();
	}

	public void commonCategories() throws IOException {
		List<String> recordsInFile = new ArrayList<>();
		String fileName = "dataseeding/common_category.txt";
		try {
			ClassPathResource resource = new ClassPathResource(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			recordsInFile = reader.lines().collect(Collectors.toList());
			reader.close();
			List<CommonCategories> commoncategories = commonCategoriesRepository.findAll();
			if (recordsInFile != null && !recordsInFile.isEmpty()) {
				// this.commonCategoriesRepository.trancateCommonCategories();
				for (String record : recordsInFile) {
					if (null != record) {
						// split record with comma separated
						String[] recordFields = record.split(",");
						CommonCategories categories = new CommonCategories();
						for (int i = 0; i < recordFields.length; i++) {
							// split field with : separated
							String[] withFieldName = recordFields[i].split(":");
							if ((withFieldName[0].trim()).equalsIgnoreCase(DataSeedingEnum.NAME.getColumnName())) {
								categories.setName(withFieldName[1].trim());
							} else if ((withFieldName[0].trim())
									.equalsIgnoreCase(DataSeedingEnum.DESCRIPTION.getColumnName())) {
								categories.setDescription(withFieldName[1].trim());
							} else if (null != withFieldName[0].trim() && (withFieldName[0].trim())
									.equalsIgnoreCase(DataSeedingEnum.STATUS.getColumnName())) {
								categories.setStatus(Integer.parseInt(withFieldName[1].trim()));
							}
						}

						Boolean isSameName = false;
						if (null != categories && categories.getName() != null) {
							if (commoncategories != null && !commoncategories.isEmpty())
								for (CommonCategories commonCategory : commoncategories) {
									if (commonCategory != null && commonCategory.getName() != null ) {
										if(commonCategory.getName().equalsIgnoreCase(categories.getName()))
											isSameName = ApplicationConstants.TRUE;
									}
								}
							if (!isSameName) {
								this.commonCategoriesRepository.save(categories);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void commonStatus() throws IOException {
		List<String> recordsInFile = new ArrayList<>();
		String fileName = "dataseeding/common_status.txt";
		try {
			ClassPathResource resource = new ClassPathResource(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			recordsInFile = reader.lines().collect(Collectors.toList());
			reader.close();
			List<CommonStatus> statusList = commonStatusRepository.findAll();
			// this.commonStatusRepository.trancateCommonStatus();
			if (recordsInFile != null && !recordsInFile.isEmpty()) {

				for (String record : recordsInFile) {
					if (null != record) {
						// split record with comma separated
						String[] recordFields = record.split(",");
						CommonStatus commonStatus = new CommonStatus();
						for (int i = 0; i < recordFields.length; i++) {
							// split field with : separated
							String[] withFieldName = recordFields[i].split(":");
							if ((withFieldName[0].trim()).equalsIgnoreCase(DataSeedingEnum.NAME.getColumnName())) {
								commonStatus.setName(withFieldName[1].trim());
							} else if ((withFieldName[0].trim())
									.equalsIgnoreCase(DataSeedingEnum.DESCRIPTION.getColumnName())) {
								commonStatus.setDescription(withFieldName[1].trim());
							} else if (null != withFieldName[0].trim() && (withFieldName[0].trim())
									.equalsIgnoreCase(DataSeedingEnum.STATUS.getColumnName())) {
								commonStatus.setStatus(Integer.parseInt(withFieldName[1].trim()));
							} else if (null != withFieldName[0].trim() && (withFieldName[0].trim())
									.equalsIgnoreCase(DataSeedingEnum.CATEGORY_NAME.getColumnName())) {
								CommonCategories commoncategory = commonCategoriesRepository
										.findByName(withFieldName[1].trim());
								if (commoncategory != null && commoncategory.getId() != null)
									commonStatus.setCategoryId(commoncategory.getId());
							}
						}

						Boolean isSameName = false;
						if (null != commonStatus && commonStatus.getName() != null) {
							if (statusList != null && !statusList.isEmpty())
								for (CommonStatus commonStatu : statusList) {
									if (commonStatu.getName().equalsIgnoreCase(commonStatus.getName())) {
										isSameName = ApplicationConstants.TRUE;
									}
								}
							if (!isSameName) {
								this.commonStatusRepository.save(commonStatus);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
