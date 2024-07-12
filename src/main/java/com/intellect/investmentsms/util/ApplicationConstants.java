package com.intellect.investmentsms.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.env.AbstractEnvironment;

import com.intellect.investmentsms.InvestmentsMsApplication;

public class ApplicationConstants {
	static Properties prop = new Properties();
	static {
		try {
			prop.load(InvestmentsMsApplication.class.getClassLoader().getResourceAsStream("constant.properties"));
		} catch (IOException ex) {
		}
	}

	static Properties properties = new Properties();

	static {
		try {
			properties.load(InvestmentsMsApplication.class.getClassLoader()
//					.getResourceAsStream("config/application-" + "dev" + ".properties"));
					.getResourceAsStream("config/application-"
							+ System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME) + ".properties"));
		} catch (IOException ex) {
		}
	}
	public static final String HEADER_USERID = prop.getProperty("HEADER_USERID");

	public static final String AUDITABLE_ADMIN_USER = prop.getProperty("AUDITABLE_ADMIN_USER");

	public static final String CREATE_RECORD_SUCESS = prop.getProperty("CREATE_RECORD_SUCESS");

	public static final String DCO = prop.getProperty("DCO");

	public static final String UPDATE_RECORD_SUCESS = prop.getProperty("UPDATE_RECORD_SUCESS");

	public static final String DELETE_RECORD_SUCESS = prop.getProperty("DELETE_RECORD_SUCESS");

	public static final String FETCH_RECORD_SUCESS = prop.getProperty("FETCH_RECORD_SUCESS");

	public static final String CREATE_RECORD_FAILED = prop.getProperty("CREATE_RECORD_FAILED");

	public static final String UPDATE_RECORD_FAILED = prop.getProperty("UPDATE_RECORD_FAILED");

	public static final String DELETE_RECORD_FAILED = prop.getProperty("DELETE_RECORD_FAILED");

	public static final String FETCH_RECORD_FAILED = prop.getProperty("FETCH_RECORD_FAILED");

	public static final String EXCEPTION_OCCURED = prop.getProperty("EXCEPTION_OCCURED");

	public static final String SERVER_ERROR = prop.getProperty("SERVER_ERROR");

	public static final String BAD_REQUEST = prop.getProperty("BAD_REQUEST");

	public static final String HEADER_AUTH = prop.getProperty("HEADER_AUTH");

	public static final String UNAUTHRIZED_USER_MSG = prop.getProperty("UNAUTHRIZED_USER_MSG");

	public static final String RES_STATUS_SUCCESS = prop.getProperty("RES_STATUS_SUCCESS");

	public static final String RES_STATUS_ERROR = prop.getProperty("RES_STATUS_ERROR");

	public static final Integer ACTIVE = Integer.parseInt(prop.getProperty("ACTIVE"));

	public static final Integer IN_ACTIVE = Integer.parseInt(prop.getProperty("IN_ACTIVE"));

	public static final Integer ZERO = Integer.parseInt(prop.getProperty("ZERO"));

	public static final String NO_PARENT_RECORD = prop.getProperty("NO_PARENT_RECORD");

	public static final String ACTIVE_STATUS = prop.getProperty("ACTIVE_STATUS");

	public static final String IN_ACTIVE_STATUS = prop.getProperty("IN_ACTIVE_STATUS");

	public static final String YES = prop.getProperty("YES");

	public static final String NO = prop.getProperty("NO");

	public static final String WORK_FLOW_DUPLICATE = prop.getProperty("WORK_FLOW_DUPLICATE");

	public static final String COMMON_STATUS_DUPLICATE = prop.getProperty("COMMON_STATUS_DUPLICATE");

	public static final String COMMON_CATEGORIES_DUPLICATE = prop.getProperty("COMMON_CATEGORIES_DUPLICATE");

	public static final String NA = prop.getProperty("NA");

	public static final String WORKFLOW_STEPS_DUPLICATE = prop.getProperty("WORKFLOW_STEPS_DUPLICATE");

	public static final String STEP_ACTIVE_IN_ANOTHER = prop.getProperty("STEP_ACTIVE_IN_ANOTHER");

	public static final String COMMON_CATEGORY_ACTIVE_IN_COMMON_STATUS = prop.getProperty("COMMON_CATEGORY_ACTIVE_IN_COMMON_STATUS");

	public static final String EMAIL_ALREADY_EXISTS = prop.getProperty("EMAIL_ALREADY_EXISTS");

	public static final String USER_ALREADY_EXISTS = prop.getProperty("USER_ALREADY_EXISTS");

	public static final String PRODUCT_PURPOSE_CONFIG_ALREADY_EXIST = prop.getProperty("PRODUCT_PURPOSE_CONFIG_ALREADY_EXIST");
	
	public static final String PRODUCT_COLLATERAL_ALREADY_EXISTS = prop.getProperty("PRODUCT_COLLATERAL_ALREADY_EXISTS");
	
	public static final String PRODUCT_PURPOSE_CONFIG = prop.getProperty("PRODUCT_PURPOSE_CONFIG");
	
	public static final String MOBILE_NUMBER_ALREADY_EXISTS = prop.getProperty("MOBILE_NUMBER_ALREADY_EXISTS");

	public static final String USER_NOT_VALID = prop.getProperty("USER_NOT_VALID");

	public static final String OTP_NOT_VALID = prop.getProperty("OTP_NOT_VALID");

	public static final String USER_VALID = prop.getProperty("USER_VALID");

	public static final String ROLE_ALREADY_EXISTS = prop.getProperty("ROLE_ALREADY_EXISTS");

	public static final String TERM_DEPOSIT_INVESTMENT_ACCOUNTS_EXIST = prop.getProperty("TERM_DEPOSIT_INVESTMENT_ACCOUNTS_EXIST");

	public static final Boolean TRUE = Boolean.parseBoolean(prop.getProperty("TRUE"));

	public static final Boolean FALSE = Boolean.parseBoolean(prop.getProperty("FALSE"));

	public static final String USER = prop.getProperty("USER");

	public static final String SB_ACCCOUNT_SERVICE = prop.getProperty("SB_ACCCOUNT_SERVICE");

	public static final String SB_GUARDIAN_DETAILS = prop.getProperty("SB_GUARDIAN_DETAILS");

	public static final String SB_STANDING_INSTURCTIONS = prop.getProperty("SB_STANDING_INSTURCTIONS");

	public static final String SB_AMOUNT_BLOCKING_DETAILS = prop.getProperty("SB_AMOUNT_BLOCKING_DETAILS");

	public static final String SB_ACC_KYC_DETAILS = prop.getProperty("SB_ACC_KYC_DETAILS");

	public static final String SB_TRANSACTION_DETAILS = prop.getProperty("SB_TRANSACTION_DETAILS");

	public static final String ISSUED_CHEQUEBOOK_DETAILS = prop.getProperty("ISSUED_CHEQUEBOOK_DETAILS");

	public static final String CHEQUEBOOK_LEAFS_DETAILS = prop.getProperty("CHEQUEBOOK_LEAFS_DETAILS");

	public static final String SB_CASH_TRANSACTION_DENOMINATION = prop.getProperty("SB_CASH_TRANSACTION_DENOMINATION");

	public static final String CUSTOMER_PASSBOOKS = prop.getProperty("CUSTOMER_PASSBOOKS");

	public static final String DENOMINATION_TYPES_ALREADY_EXISTED = prop.getProperty("DENOMINATION_TYPES_ALREADY_EXISTED");

	public static final String TRANSACTION_MODE_ALREADY_EXISTED = prop.getProperty("TRANSACTION_MODE_ALREADY_EXISTED");

	public static final String APPLICATION_TYPE_ALREADY_EXISTED = prop.getProperty("APPLICATION_TYPE_ALREADY_EXISTED");

	public static final String TRANSACTION_MODE = prop.getProperty("TRANSACTION_MODE");

	public static final String DENOMINATION_TYPES = prop.getProperty("DENOMINATION_TYPES");

	public static final String APPLICATION_TYPES = prop.getProperty("APPLICATION_TYPES");

	public static final String ACCOUNT_TYPE_ALREADY_EXIST = prop.getProperty("ACCOUNT_TYPE_ALREADY_EXIST");

	public static final String DEBIT_CARD_TYPE_ALREADY_EXIST = prop.getProperty("DEBIT_CARD_TYPE_ALREADY_EXIST");

	public static final String SERVICE_TYPE_ALREADY_EXIST = prop.getProperty("SERVICE_TYPE_ALREADY_EXIST");

	public static final String INTEREST_POSTING_FREQUECY_ALREADY_EXIST = prop.getProperty("INTEREST_POSTING_FREQUECY_ALREADY_EXIST");

	public static final String ACCOUNT_RULES_GENERAL_CONFIG = prop.getProperty("ACCOUNT_RULES_GENERAL_CONFIG");

	public static final String INTEREST_POLICY_CONFIG = prop.getProperty("INTEREST_POLICY_CONFIG"); 

	public static final String ACCOUNT_SERVICE_CONFIG_CHARGES = prop.getProperty("ACCOUNT_SERVICE_CONFIG_CHARGES");

	public static final String PRODUCT_DEFINITION = prop.getProperty("PRODUCT_DEFINITION");

	public static final String REQUIRED_DOCUMENTS_CONFIG = prop.getProperty("REQUIRED_DOCUMENTS_CONFIG");

	public static final String GROUP_PROMOTER_KYC_DETAILS = prop.getProperty("GROUP_PROMOTER_KYC_DETAILS");

	public static final String PRODUCT_NAME_ALREADY_EXIST = prop.getProperty("PRODUCT_NAME_ALREADY_EXIST");

	public static final String SAVINGS_ACCOUNT_DETAILS = prop.getProperty("SAVINGS_ACCOUNT_DETAILS");

	public static final String WORK_FLOW = prop.getProperty("WORK_FLOW");

	public static final String WORK_FLOW_STEPS = prop.getProperty("WORK_FLOW_STEPS");

	public static final String DOCUMENT_TYPES = prop.getProperty("DOCUMENT_TYPES");

	public static final String COMMON_CATEGORY = prop.getProperty("COMMON_CATEGORY");

	public static final String COMMON_STATUS = prop.getProperty("COMMON_STATUS");

	public static final String SB_COMMUNICATION_DETAILS = prop.getProperty("SB_COMMUNICATION_DETAILS");

	public static final String SB_NOMINEE = prop.getProperty("SB_NOMINEE");

	public static final String SB_GURDIAN_DETAILS = prop.getProperty("SB_GURDIAN_DETAILS");

	public static final String UPLOAD_DIR = properties.getProperty("upload.dir");

	public static final String TERM_DEPOSIT_INVESTMENT_ACCOUNTS = prop.getProperty("TERM_DEPOSIT_INVESTMENT_ACCOUNTS"); 
	
	public static final String BORROWING_SCHEME_TYPE_EXIST = prop.getProperty("BORROWING_SCHEME_TYPE_EXIST");
	
	public static final String BORROWING_TYPE_EXIST = prop.getProperty("BORROWING_TYPE_EXIST");

	public static final String BORRROWINGS_WORK_FLOW = prop.getProperty("BORRROWINGS_WORK_FLOW");
	
	public static final String BORROWING_TYPE = prop.getProperty("BORROWING_TYPE");
	
	public static final String BORROWING_SCHEME_TYPE = prop.getProperty("BORROWING_SCHEME_TYPE");
	
	public static final String BORROWING_ACCOUNTS = prop.getProperty("BORROWING_ACCOUNTS");
	
	public static final String BORROWING_ACCOUNT_MAPPED_LOANS = prop.getProperty("BORROWING_ACCOUNT_MAPPED_LOANS");

	public static final String PRODUCT_COLLATERAL = prop.getProperty("PRODUCT_COLLATERAL");
	
	public static final String DISTRICT = prop.getProperty("DISTRICT");

	public static final String RELATION_SHIP_TYPES = prop.getProperty("RELATION_SHIP_TYPES");

	public static final String RELATIONSHIP_TYPES_ALREADY_EXIST = prop.getProperty("RELATIONSHIP_TYPES_ALREADY_EXIST");

	public static final String STATE = prop.getProperty("STATE");

	public static final String SUB_DISTRICT = prop.getProperty("SUB_DISTRICT");

	public static final String VILLAGES = prop.getProperty("VILLAGES");

	public static final String COUNRTY_ACTIVE_IN_STATE = prop.getProperty("COUNRTY_ACTIVE_IN_STATE");

	public static final String TERM_DEPOSIT_INVESTMENT_ACCOUNTS_ACTIVE_IN_DISTRICT = prop.getProperty("TERM_DEPOSIT_INVESTMENT_ACCOUNTS_ACTIVE_IN_DISTRICT");

	public static final String COUNRTY_ACTIVE_IN_SUB_DISTRICT = prop.getProperty("COUNRTY_ACTIVE_IN_SUB_DISTRICT");

	public static final String COUNRTY_ACTIVE_IN_VILLAGE = prop.getProperty("COUNRTY_ACTIVE_IN_VILLAGE");

	public static final String DISTRICT_EXIST = prop.getProperty("DISTRICT_EXIST");

	public static final String DISTRICT_ACTIVE_IN_SUB_DISTRICT = prop.getProperty("DISTRICT_ACTIVE_IN_SUB_DISTRICT");

	public static final String DISTRICT_ACTIVE_IN_VILLAGE = prop.getProperty("DISTRICT_ACTIVE_IN_VILLAGE");

	public static final String STATE_EXIST = prop.getProperty("STATE_EXIST");

	public static final String STATE_ACTIVE_IN_DISTRICT = prop.getProperty("STATE_ACTIVE_IN_DISTRICT");

	public static final String STATE_ACTIVE_IN_SUB_DISTRICT = prop.getProperty("STATE_ACTIVE_IN_SUB_DISTRICT");

	public static final String STATE_ACTIVE_IN_VILLAGE = prop.getProperty("STATE_ACTIVE_IN_VILLAGE");

	public static final String ONE_STATE_ACTIVE = prop.getProperty("ONE_STATE_ACTIVE");

	public static final String SUBDISTRICT_EXIST = prop.getProperty("SUBDISTRICT_EXIST");

	public static final String SUB_DISTRICT_ACTIVE_IN_VILLAGE = prop.getProperty("SUB_DISTRICT_ACTIVE_IN_VILLAGE");

	public static final String VILLAGE_ALREADY_EXIST = prop.getProperty("VILLAGE_ALREADY_EXIST");

	public static final String JOB_SCHEDULER = "0 * * * * *";

	public static final String DOCUMENT_TYPE_ALREADY_EXIST = prop.getProperty("DOCUMENT_TYPE_ALREADY_EXIST");

	public static final String COMMUNITY_EXIST = prop.getProperty("COMMUNITY_EXIST");

	public static final String CASTE = prop.getProperty("CASTE");

	public static final String COMMUNITY = prop.getProperty("COMMUNITY");

	public static final String SOCIETY_BRANCH = prop.getProperty("SOCIETY_BRANCH");

	public static final String MEMBER_TYPES_ALREADY_EXIST = prop.getProperty("MEMBER_TYPES_ALREADY_EXIST");

	public static final String GROUP_PROMOTERS = prop.getProperty("GROUP_PROMOTERS");

	public static final String INSTITUTION_PROMOTER_DETAILS = prop.getProperty("INSTITUTION_PROMOTER_DETAILS");

	public static final String MEMBER_BASIC_DETAILS = prop.getProperty("MEMBER_BASIC_DETAILS");

	public static final String MEMBERSHIP_GROUP_DETAILS = prop.getProperty("MEMBERSHIP_GROUP_DETAILS");

	public static final String MEMBER_TYPES = prop.getProperty("MEMBER_TYPES");

	public static final String MEM_INSTITUTION = prop.getProperty("MEM_INSTITUTION");

	public static final String OCCUPATION_TYPES_ALREADY_EXISTS = prop.getProperty("OCCUPATION_TYPES_ALREADY_EXISTS");

	public static final String OPERATION_TYPES_EXIST = prop.getProperty("OPERATION_TYPES_EXIST");

	public static final String OCCUPATION_TYPES = prop.getProperty("OCCUPATION_TYPES");

	public static final String OPERATION_TYPES = prop.getProperty("OPERATION_TYPES");

	public static final String QUALIFICATION = prop.getProperty("QUALIFICATION");
	
	public static final String PRODUCT_TYPES = prop.getProperty("PRODUCT_TYPES");

	public static final String STATUS_ACTIVE_IN_CHILD = prop.getProperty("STATUS_ACTIVE_IN_CHILD");

	public static final String QUALIFICATION_TYPES_ALREADY_EXISTS = prop.getProperty("QUALIFICATION_TYPES_ALREADY_EXISTS");

	public static final String QUALIFICATION_DELETE_OR_INACTIVE_FAILED_MSG = prop.getProperty("QUALIFICATION_TYPES_ALREADY_EXISTS");

	public static final String QUALIFICATION_IN_ACTIVE = prop.getProperty("QUALIFICATION_IN_ACTIVE");

	public static final String ADMISSION_NUMBER_ALREADY_EXISTED = prop.getProperty("ADMISSION_NUMBER_ALREADY_EXISTED");

	public static final String INSTITUTION_NAME_ALREADY_EXIST = prop.getProperty("INSTITUTION_NAME_ALREADY_EXIST");

	public static final String FINANCIAL_BANK_DETAILS = prop.getProperty("FINANCIAL_BANK_DETAILS");
	
	public static final Integer SUCCESS_STATUS = Integer.parseInt(prop.getProperty("SUCCESS_STATUS"));

	public static final Integer FAIL_STATUS = Integer.parseInt(prop.getProperty("FAIL_STATUS"));

	public static final String INSUFFICIENT_BALANCE = prop.getProperty("INSUFFICIENT_BALANCE");

	public static final String INVALID_MIN_DEPOSIT = prop.getProperty("INVALID_MIN_DEPOSIT");

	public static final String INVALID_MAX_DEPOSIT = prop.getProperty("INVALID_MAX_DEPOSIT");

	public static final String CHARGES_COLLECTED = prop.getProperty("CHARGES_COLLECTED");

	public static final String TO_CHARGES = prop.getProperty("TO_CHARGES");

	public static final String TO_STANDING_INSTRUCTION = prop.getProperty("TO_STANDING_INSTRUCTION");

	public static final String INVALID_CHEQUE = prop.getProperty("INVALID_CHEQUE");

	public static final String BY_CHARGES = prop.getProperty("BY_CHARGES");

	public static final String INVALID_MAX_WITHDRAW_WITH_PAN = prop.getProperty("INVALID_MAX_WITHDRAW_WITH_PAN");

	public static final String INVALID_MAX_TRANSFER_AMOUNT = prop.getProperty("INVALID_MAX_TRANSFER_AMOUNT");

	public static final String INVLAID_MAX_WITHDRAW_WITH_CHEQUE = prop.getProperty("INVLAID_MAX_WITHDRAW_WITH_CHEQUE");

	public static final String ACCOUNT_BALANCE_LESS_THAN_MIN_BALANCE = prop.getProperty("ACCOUNT_BALANCE_LESS_THAN_MIN_BALANCE");

	public static final String CALCULATED_INTEREST_AMOUNT_LESS_THAN_MIN_INTEREST_AMOUNT = prop.getProperty("CALCULATED_INTEREST_AMOUNT_LESS_THAN_MIN_INTEREST_AMOUNT");

	public static final String INTEREST_ADJUSTED_FOR_SERVICE_CHARGES = prop.getProperty("INTEREST_ADJUSTED_FOR_SERVICE_CHARGES");

	public static final String ACCOUNT_TYPES = prop.getProperty("ACCOUNT_TYPES");

	public static final String AUTO_PAYMENTS_DETAILS = prop.getProperty("AUTO_PAYMENTS_DETAILS");

	public static final String CHEQUE_TRANSACTION_DETAILS = prop.getProperty("CHEQUE_TRANSACTION_DETAILS");

	public static final String AUTO_PAYMENTS_SUMMARY = prop.getProperty("AUTO_PAYMENTS_SUMMARY");

	public static final String DEBIT_CARD_TYPES = prop.getProperty("DEBIT_CARD_TYPES");

	public static final String BORROWINGS_WORK_FLOW = prop.getProperty("BORROWINGS_WORK_FLOW");

	public static final String INTEREST_POSTING_DETAILS = prop.getProperty("INTEREST_POSTING_DETAILS");

	public static final String INTEREST_POSTING_FREQUENCY = prop.getProperty("INTEREST_POSTING_FREQUENCY");

	public static final String INTEREST_POSTING_SUMMARY = prop.getProperty("INTEREST_POSTING_SUMMARY");

	public static final String PACS_DETAILS = prop.getProperty("PACS_DETAILS");

	public static final String TRANSFER_TRANSACTION_DETAILS = prop.getProperty("TRANSFER_TRANSACTION_DETAILS");

	public static final String TRANSACTION_LIMIT_CONFIG = prop.getProperty("TRANSACTION_LIMIT_CONFIG");

	public static final String STANDING_INSTRUCTION_PAYMENTS = prop.getProperty("STANDING_INSTRUCTION_PAYMENTS");

	public static final String SERVICE_TYPES = prop.getProperty("SERVICE_TYPES");

	public static final String SB_JOINT_ACC_HOLDER_DETAILS = prop.getProperty("SB_JOINT_ACC_HOLDER_DETAILS");

	public static final String SB_FORM_TYPES = prop.getProperty("SB_FORM_TYPES");

	public static final String SB_ACCOUNT_SERVICE_PAYMENTS = prop.getProperty("SB_ACCOUNT_SERVICE_PAYMENTS");

	public static final String SAVING_ACCOUNT = prop.getProperty("SAVING_ACCOUNT");

	public static final String SAVING_ACCOUNT_DETAILS_SUMMARY = prop.getProperty("SAVING_ACCOUNT_DETAILS_SUMMARY");
	
	public static final String INTEREST_POLICY_CONFIG_ALREADY_EXISTED = prop.getProperty("INTEREST_POLICY_CONFIG_ALREADY_EXISTED");
	
	public static final String SETTINGS = prop.getProperty("SETTINGS");
	
	public static final String PRODUCT_CHARGES_CONFIG_ALREADY_EXIST = prop.getProperty("PRODUCT_CHARGES_CONFIG_ALREADY_EXIST");
	
	public static final String PRODUCT_APPORTION_CONFIG_ALREADY_EXIST = prop.getProperty("PRODUCT_APPORTION_CONFIG_ALREADY_EXIST");
	
	public static final String PRODUCT_APPORTION_CONFIG = prop.getProperty("PRODUCT_APPORTION_CONFIG");
	
	public static final String PRODUCT_CHARGES_CONFIG = prop.getProperty("PRODUCT_CHARGES_CONFIG");

	public static final String BORROWING_LINKED_SHARECAPITAL = prop.getProperty("BORROWING_LINKED_SHARECAPITAL");
	
	public static final String PRODUCT_REQUIRED_DOCUMENTS = prop.getProperty("PRODUCT_REQUIRED_DOCUMENTS");
	
	public static final String PRODUCT_EXIST = prop.getProperty("PRODUCT_EXIST");
	
	public static final String PRODUCT = prop.getProperty("PRODUCT");
	
	public static final String INTEREST_POLICY_CONFIG_ALREADY_EXIST = prop.getProperty("INTEREST_POLICY_CONFIG_ALREADY_EXIST");

	public static final String CHARGES_COLLECTION_DETAILS = prop.getProperty("CHARGES_COLLECTION_DETAILS");

	public static final String BORROWING_DISBURSMENT= prop.getProperty("BORROWING_DISBURSMENT");

	public static final String BORROWING_ACCOUNT_DOCUMENTS = prop.getProperty("BORROWING_ACCOUNT_DOCUMENTS");
	
	public static final String EMI_CHART = prop.getProperty("EMI_CHART");

	public static final String BORROWING_COLLECTION_RESOURCE = prop.getProperty("BORROWING_COLLECTION_RESOURCE");

	public static final String BORROWING_ACCOUNT_DOCUMENTS_ALREADY_EXIST = prop.getProperty("BORROWING_ACCOUNT_DOCUMENTS_ALREADY_EXIST");
	
	public static final String BORROWING_LINKED_SHARECAPITAL_EXIST = prop.getProperty("BORROWING_LINKED_SHARECAPITAL_EXIST");
	
	public static final String PRODUCT_REQUIRED_DOCUMENTS_EXIST = prop.getProperty("PRODUCT_REQUIRED_DOCUMENTS_ALREADY_EXIST");
	
	public static final String SAO_BORROWING_ACCOUNT_DOCUMENTS = prop.getProperty("SAO_BORROWING_ACCOUNT_DOCUMENTS");
	
	public static final String SAO_BORROWING_ACCOUNT_MAPPED_LOANS = prop.getProperty("SAO_BORROWING_ACCOUNT_MAPPED_LOANS");
	
	public static final String SAO_BORROWING_ACCOUNTS = prop.getProperty("SAO_BORROWING_ACCOUNTS");
	
	public static final String SAO_BORROWING_COLLECTION_RESOURCE = prop.getProperty("SAO BORROWING_COLLECTION_RESOURCE");
	
	public static final String SAO_BORROWING_DISBURSMENT= prop.getProperty("SAO BORROWING_DISBURSMENT");
	
	public static final String BORROWING_LINKED_SHARECAPTIAL= prop.getProperty("BORROWING_LINKED_SHARECAPTIAL");
	
	public static final String BORROWING_LINKED_SHARECAPTIAL_EXIST= prop.getProperty("BORROWING_LINKED_SHARECAPTIAL_EXIST");

	public static final String INTEREST_PAYMENTS = prop.getProperty("INTEREST_PAYMENTS");

	public static final String PRODUCT_ASSOCIATED_BANK_DETAILS = prop.getProperty("PRODUCT_ASSOCIATED_BANK_DETAILS");

	public static final String INVESTMENTS_WORK_FLOW = prop.getProperty("INVESTMENTS_WORK_FLOW");

	public static final String PENALTY_CONFIG = prop.getProperty("PENALTY_CONFIG");

	public static final String REQUIRED_DOCUMENTS = prop.getProperty("REQUIRED_DOCUMENTS");

	public static final String PENALTY_CONFIG_ALREADY_EXIST = prop.getProperty("PENALTY_CONFIG_ALREADY_EXIST");

	public static final String REQUIRED_DOCUMENTS_ALREADY_EXIST = prop.getProperty("REQUIRED_DOCUMENTS_ALREADY_EXIST");
	
	public static final String TERM_DEPOSIT_INVESTMENT_ACCOUNT = prop.getProperty("TERM_DEPOSIT_INVESTMENT_ACCOUNT");
	
	public static final String INVESTMENT_ACCOUNT_INSTALLMENTS = prop.getProperty("INVESTMENT_ACCOUNT_INSTALLMENTS"); 
	
	public static final String INVESTMENT_ACCOUNTS_TRANSACTION = prop.getProperty("INVESTMENT_ACCOUNTS_TRANSACTION");
	
	public static final String INVESTMENT_ACCOUNT_DOCUMENT = prop.getProperty("INVESTMENT_ACCOUNT_DOCUMENT");
	
	public static final String SHARES_INVESTMENT_ACCOUNT = prop.getProperty("SHARES_INVESTMENT_ACCOUNT");
	
	public static final String INTEREST_POLICY_ALREADY_EXIST = prop.getProperty("INTEREST_POLICY_ALREADY_EXIST");

	public static final String CARD_RATES = prop.getProperty("CARD_RATES");
	
	public static final String MIN_OR_MAX_TENURE_ALREADY_EXIST = prop.getProperty("MIN_OR_MAX_TENURE_ALREADY_EXIST");

	public static final String BANK_ALREADY_EXIST = prop.getProperty("BANK_ALREADY_EXIST");
	
	public static final String INVESTED_BANK_DETAILS =  prop.getProperty("INVESTED_BANK_DETAILS");

	public static final String PRODUCT_ALREADY_EXIST = prop.getProperty("PRODUCT_ALREADY_EXIST");

	public static final String CERTIFICATE_NUMBER_ALREADY_EXIST = prop.getProperty("CERTIFICATE_NUMBER_ALREADY_EXIST");

	public static final String DEPOSIT_AND_SHARES_INVESTMENT_ACCOUNTS = prop.getProperty("DEPOSIT_AND_SHARES_INVESTMENT_ACCOUNTS");

	public static final String DOCUMENT_TYPES_DUPLICATE = prop.getProperty("DOCUMENT_TYPES_DUPLICATE");
}
