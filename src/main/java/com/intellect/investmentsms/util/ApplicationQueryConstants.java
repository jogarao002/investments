package com.intellect.investmentsms.util;

public class ApplicationQueryConstants {
	
	public static final String TRUNCATE_COMMON_CATEGORIES = "TRUNCATE TABLE common_categories";
	
	public static final String TRUNCATE_COMMON_STATUS = "TRUNCATE TABLE common_status";

	public static final String TRUNCATE_INFRA_QUESTIONS = "TRUNCATE TABLE infra_question";

	//public static final String TRUNCATE_MENUS = "TRUNCATE TABLE menus";
	
	public static final String NON_CREDIT_BUSINESS_REPORT_QUERY = "SELECT dccb.name 'dccb_name', pacs_details.name 'pacs_name', \n" + 
			"sum(if(fhr_prod_details.sub_product_id in (123),fhr_prod_details.prifle_count,0)) 'procurement_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (123),ROUND((fhr_prod_details.turover / 100000), 2),0))  'procurement_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (124),fhr_prod_details.prifle_count,0)) 'merchandise_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (124),ROUND((fhr_prod_details.turover / 100000), 2),0))  'merchandise_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (125),fhr_prod_details.prifle_count,0)) 'fertilizer_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (125),ROUND((fhr_prod_details.turover / 100000), 2),0))  'fertilizer_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (126),fhr_prod_details.prifle_count,0)) 'pesticides_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (126),ROUND((fhr_prod_details.turover / 100000), 2),0))  'pesticides_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (127),fhr_prod_details.prifle_count,0)) 'pds_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (127),ROUND((fhr_prod_details.turover / 100000), 2),0))  'pds_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (128,136),fhr_prod_details.prifle_count,0)) 'patanjali_store_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (128,136),ROUND((fhr_prod_details.turover / 100000), 2),0))  'patanjali_store_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (129),fhr_prod_details.prifle_count,0)) 'seeds_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (129),ROUND((fhr_prod_details.turover / 100000), 2),0))  'seeds_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (130),fhr_prod_details.prifle_count,0)) 'cold_storage_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (130),ROUND((fhr_prod_details.turover / 100000), 2),0))  'cold_storage_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (132,135),fhr_prod_details.prifle_count,0)) 'warehouse_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (132,135),ROUND((fhr_prod_details.turover / 100000), 2),0))  'warehouse_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (133),fhr_prod_details.prifle_count,0)) 'wheat_paddy_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (133),ROUND((fhr_prod_details.turover / 100000), 2),0))  'wheat_paddy_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (134,138),fhr_prod_details.prifle_count,0)) 'petrol_pump_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (134,138),ROUND((fhr_prod_details.turover / 100000), 2),0))  'petrol_pump_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (137),fhr_prod_details.prifle_count,0)) 'hotel_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (137),ROUND((fhr_prod_details.turover / 100000), 2),0))  'hotel_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (139),fhr_prod_details.prifle_count,0)) 'haat_bazar_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (139),ROUND((fhr_prod_details.turover / 100000), 2),0))  'haat_bazar_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (142),fhr_prod_details.prifle_count,0)) 'jaivik_capacity',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (142),ROUND((fhr_prod_details.turover / 100000), 2),0))  'jaivik_turnOver',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (143),fhr_prod_details.prifle_count,0)) consumer_business_capacity, \n" + 
			"sum(if(fhr_prod_details.sub_product_id in (143),ROUND((fhr_prod_details.turover / 100000), 2),0)) consumer_business_turnOver \n" + 
			"FROM fhr_prod_details \n" + 
			"join pacs_details on pacs_details.id = fhr_prod_details.pacs_id\n" + 
			"join dccb on dccb.id = pacs_details.dccb_id\n" + 
			"where product_id = ?1 and dccb.id = ?2 group by pacs_details.id";
	
	public static final String DEPOSITS_REPORT_QUERY = "SELECT dccb.name 'dccb_name', pacs_details.name 'pacs_name', \n" + 
			"sum(if(fhr_prod_details.sub_product_id in (3),fhr_prod_details.prifle_count,0)) saving_deposits_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (3),ROUND((fhr_prod_details.turover / 100000), 2),0))  saving_deposits_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (4),fhr_prod_details.prifle_count,0)) current_deposits_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (4),ROUND((fhr_prod_details.turover / 100000), 2),0))  current_deposits_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (8),fhr_prod_details.prifle_count,0)) contribution_on_kcc_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (8),ROUND((fhr_prod_details.turover / 100000), 2),0))  contribution_on_kcc_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (9),fhr_prod_details.prifle_count,0)) recurring_deposits_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (9),ROUND((fhr_prod_details.turover / 100000), 2),0))  recurring_deposits_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (10),fhr_prod_details.prifle_count,0)) term_deposit_non_cummulative_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (10),ROUND((fhr_prod_details.turover / 100000), 2),0)) term_deposit_non_cummulative_turnover,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (12),fhr_prod_details.prifle_count,0)) swayam_upadi_deposits_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (12),ROUND((fhr_prod_details.turover / 100000), 2),0))  swayam_upadi_deposits_turnover,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (13),fhr_prod_details.prifle_count,0)) staff_security_deposits_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (13),ROUND((fhr_prod_details.turover / 100000), 2),0))  staff_security_deposits_turnover,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (16),fhr_prod_details.prifle_count,0)) prasanna_deposit_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (16),ROUND((fhr_prod_details.turover / 100000), 2),0))  prasanna_deposit_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (17),fhr_prod_details.prifle_count,0)) thrift_deposits_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (17),ROUND((fhr_prod_details.turover / 100000), 2),0))  thrift_deposits_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (19),fhr_prod_details.prifle_count,0)) fixed_deposits_cummulative_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (19),ROUND((fhr_prod_details.turover / 100000), 2),0))  fixed_deposits_cummulative_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (20),fhr_prod_details.prifle_count,0)) fixed_deposits_non_cummulative_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (20),ROUND((fhr_prod_details.turover / 100000), 2),0)) fixed_deposits_non_cummulative_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (21),fhr_prod_details.prifle_count,0)) mini_bank_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (21),ROUND((fhr_prod_details.turover / 100000), 2),0))  mini_bank_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (23),fhr_prod_details.prifle_count,0)) self_help_group_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (23),ROUND((fhr_prod_details.turover / 100000), 2),0))  self_help_group_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (144),fhr_prod_details.prifle_count,0)) term_deposit_cummulative_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (144),ROUND((fhr_prod_details.turover / 100000), 2),0))  term_deposit_cummulative_turnOver\n" + 
			"FROM fhr_prod_details \n" + 
			"join pacs_details on pacs_details.id = fhr_prod_details.pacs_id\n" + 
			"join dccb on dccb.id = pacs_details.dccb_id\n" + 
			"where product_id = ?1 and dccb.id = ?2 group by pacs_details.id;";
	
	public static final String LENDING_REPORT_QUERY = "SELECT dccb.name 'dccb_name', pacs_details.name 'pacs_name',\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (24,25,66,74,82,83,95,99,101,103,105,107,108,111,112,116,118,120,121),fhr_prod_details.prifle_count,0)) st_loan_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (24,25,66,74,82,83,95,99,101,103,105,107,108,111,112,116,118,120,121),ROUND((fhr_prod_details.turover / 100000), 2),0))  st_loan_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (34,39,53,55,64,67,69,78,85,96,104,106,109,110,113,115,117,119,122),fhr_prod_details.prifle_count,0)) mt_loan_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (34,39,53,55,64,67,69,78,85,96,104,106,109,110,113,115,117,119,122),ROUND((fhr_prod_details.turover / 100000), 2),0))  mt_loan_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (33),fhr_prod_details.prifle_count,0)) special_component_scheme_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (33),ROUND((fhr_prod_details.turover / 100000), 2),0))  special_component_scheme_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (46),fhr_prod_details.prifle_count,0)) icdp_loans_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (46),ROUND((fhr_prod_details.turover / 100000), 2),0))  icdp_loans_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (65),fhr_prod_details.prifle_count,0)) pd_loans_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (65),ROUND((fhr_prod_details.turover / 100000), 2),0))  pd_loans_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (75),fhr_prod_details.prifle_count,0)) crop_loan_convert_to_term_loan_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (75),ROUND((fhr_prod_details.turover / 100000), 2),0))  crop_loan_convert_to_term_loan_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (80),fhr_prod_details.prifle_count,0)) cash_credit_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (80),ROUND((fhr_prod_details.turover / 100000), 2),0)) cash_credit_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (91),fhr_prod_details.prifle_count,0)) fd_loan_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (91),ROUND((fhr_prod_details.turover / 100000), 2),0))  fd_loan_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (98),fhr_prod_details.prifle_count,0)) merchandise_fertilizers_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (98),ROUND((fhr_prod_details.turover / 100000), 2),0))  merchandise_fertilizers_turnOver,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (102),fhr_prod_details.prifle_count,0)) other_capacity,\n" + 
			"sum(if(fhr_prod_details.sub_product_id in (102),ROUND((fhr_prod_details.turover / 100000), 2),0))  other_turnOver\n" + 
			"FROM fhr_prod_details \n" + 
			"join pacs_details on pacs_details.id = fhr_prod_details.pacs_id\n" + 
			"join dccb on dccb.id = pacs_details.dccb_id\n" + 
			"where product_id = ?1 and dccb.id = ?2 group by pacs_details.id";
	
	public static final String DEPOSITS_SUMMARY_DATA_BY_DCCB = "SELECT sub_products.name name, sum(fhr_prod_details.prifle_count) profile_count, ROUND((sum(fhr_prod_details.turover) / 100000), 2) amount, count(pacs_id) pacs_count FROM fhr_prod_details \n"
			+ "join pacs_details on pacs_details.id = fhr_prod_details.pacs_id\n"
			+ "join dccb on dccb.id = pacs_details.dccb_id\n"
			+ "join sub_products on fhr_prod_details.sub_product_id = sub_products.id\n"
			+ "where  fhr_prod_details.product_id = ?1 and dccb.id = ?2 group by fhr_prod_details.sub_product_id";
	
	public static final String LENDING_SUMMARY_DATA_BY_DCCB = "SELECT sub_products.name name, sum(fhr_prod_details.prifle_count) profile_count, ROUND((sum(fhr_prod_details.turover) / 100000), 2) amount, count(pacs_id) pacs_count FROM fhr_prod_details \n" + 
			"join pacs_details on pacs_details.id = fhr_prod_details.pacs_id\n" + 
			"join dccb on dccb.id = pacs_details.dccb_id\n" + 
			"join sub_products on fhr_prod_details.sub_product_id = sub_products.id\n" + 
			"where  fhr_prod_details.product_id = ?1 and dccb.id = ?2\n" + 
			"and fhr_prod_details.sub_product_id in (?3)";
	
	public static final String PACS_DETAIL_REPORT = "SELECT \n" + 
			"    IFNULL(dccb.name, '') AS dccb_name,\n" + 
			"    dccb.is_two_tier,\n" + 
			"    IFNULL(dccbbr.name, '') AS dccb_branch_name,\n" + 
			"    IFNULL(pacs_details.name, '') pacs_name,\n" + 
			"    IFNULL(comm.name, '') AS ceo_name,\n" + 
			"    IFNULL(comm.mobile_number, '') ceo_mobile_number,\n" + 
			"    CONCAT(IFNULL(users.first_name, ''),\n" + 
			"            ' ',\n" + 
			"            IFNULL(users.last_name, '')) onboarded_name,\n" + 
			"    IFNULL(users.comm_mobile, '') onboarded_mobile_number,\n" + 
			"    IFNULL(cs.name, '') status\n" + 
			"FROM\n" + 
			"    pacs_details\n" + 
			"        JOIN\n" + 
			"    dccb ON dccb.id = pacs_details.dccb_id\n" + 
			"        LEFT JOIN\n" + 
			"    dccbbr ON dccbbr.id = pacs_details.dccbbr_id\n" + 
			"        LEFT JOIN\n" + 
			"    fhrcomm_details comm ON comm.pacs_id = pacs_details.id\n" + 
			"        AND comm.comm_type = (SELECT \n" + 
			"            id\n" + 
			"        FROM\n" + 
			"            communication_types\n" + 
			"        WHERE\n" + 
			"            name = 'CEO / Secretary /  Sachiv (Staff)')\n" + 
			"        JOIN\n" + 
			"    users ON users.id = pacs_details.created_by\n" + 
			"        LEFT JOIN\n" + 
			"    common_status cs ON cs.id = pacs_details.fhr_approval_status\n" + 
			"ORDER BY dccb.id,dccbbr.id,is_two_tier";

	public static final String FVR_PACS_DETAIL_REPORT = "SELECT \n" + 
			"    IFNULL(dccb.name, '') AS dccb_name,\n" + 
			"    dccb.is_two_tier,\n" + 
			"    IFNULL(dccbbr.name, '') AS dccb_branch_name,\n" + 
			"    IFNULL(fvr_pacs_details.fvr_name, '') pacs_name,\n" + 
			"    IFNULL(comm.fvr_name, '') AS ceo_name,\n" + 
			"    IFNULL(comm.fvr_mobile_number, '') ceo_mobile_number,\n" + 
			"    CONCAT(IFNULL(users.first_name, ''),\n" + 
			"            ' ',\n" + 
			"            IFNULL(users.last_name, '')) onboarded_name,\n" + 
			"    IFNULL(users.comm_mobile, '') onboarded_mobile_number,\n" + 
			"    IFNULL(cs.name, '') status\n" + 
			"FROM\n" + 
			"    fvr_pacs_details\n" + 
			"       JOIN\n" + 
			"    dccb ON dccb.id = fvr_pacs_details.fvr_dccb_id\n" + 
			"        LEFT JOIN\n" + 
			"    dccbbr ON dccbbr.id = fvr_pacs_details.fvr_dccbbr_id\n" + 
			"        LEFT JOIN\n" + 
			"    fvr_pacs_communication_details comm ON comm.pacs_id = fvr_pacs_details.pacs_id\n" + 
			"        AND comm.comm_type = (SELECT \n" + 
			"            id\n" + 
			"        FROM\n" + 
			"            communication_types\n" + 
			"        WHERE\n" + 
			"            name = 'CEO / Secretary /  Sachiv (Staff)')\n" + 
			"		LEFT JOIN\n" + 
			"    pacs_details ON pacs_details.id = fvr_pacs_details.pacs_id\n" + 
			"        LEFT JOIN\n" + 
			"    users ON users.id = pacs_details.created_by\n" + 
			"        LEFT JOIN\n" + 
			"    common_status cs ON cs.id = fvr_pacs_details.fvr_status\n" + 
			"ORDER BY dccb.id,dccbbr.id,is_two_tier";
	
	public static final String FHR_FVR_PACS_DETAIL_REPORT_WITH_STATUS = "SELECT  \n" + 
			"    IFNULL(dccb.name, '') AS dccb_name, \n" + 
			"    dccb.is_two_tier, \n" + 
			"    IFNULL(dccbbr.name, '') AS dccb_branch_name, \n" + 
			"    IFNULL(fvr_pacs_details.fvr_name, '') pacs_name, \n" + 
			"    IFNULL(comm.fvr_name, '') AS ceo_name, \n" + 
			"    IFNULL(comm.fvr_mobile_number, '') ceo_mobile_number, \n" + 
			"    CONCAT(IFNULL(users.first_name, ''), \n" + 
			"            ' ', \n" + 
			"            IFNULL(users.last_name, '')) onboarded_name, \n" + 
			"    IFNULL(users.comm_mobile, '') onboarded_mobile_number, \n" + 
			"    IFNULL(cs.name, '') status,\n" + 
			"    IFNULL(css.name, '') fhr_status_name \n" + 
			"FROM \n" + 
			"    fvr_pacs_details \n" + 
			"       JOIN \n" + 
			"    dccb ON dccb.id = fvr_pacs_details.fvr_dccb_id \n" + 
			"        LEFT JOIN \n" + 
			"    dccbbr ON dccbbr.id = fvr_pacs_details.fvr_dccbbr_id \n" + 
			"        LEFT JOIN \n" + 
			"    fvr_pacs_communication_details comm ON comm.pacs_id = fvr_pacs_details.pacs_id \n" + 
			"        AND comm.comm_type = (SELECT  \n" + 
			"            id \n" + 
			"        FROM \n" + 
			"            communication_types \n" + 
			"        WHERE \n" + 
			"            name = 'CEO / Secretary /  Sachiv (Staff)') \n" + 
			"		LEFT JOIN \n" + 
			"    pacs_details ON pacs_details.id = fvr_pacs_details.pacs_id \n" + 
			"        LEFT JOIN \n" + 
			"    users ON users.id = pacs_details.created_by \n" + 
			"        LEFT JOIN \n" + 
			"    common_status cs ON cs.id = fvr_pacs_details.fvr_status \n" + 
			"        LEFT JOIN \n" + 
			"    common_status css ON css.id = pacs_details.fhr_approval_status \n" + 
			"ORDER BY dccb.id,dccbbr.id,is_two_tier";
	
	
	public static final String Get_MISMATCHED_LENDING_SAMPLING_FILES = "select * from lending where filed_path like 'L%'";
	
	public static final String Get_MISMATCHED_DEPOSITS_SAMPLING_FILES = "select * from deposits where filed_path like 'D%'";
	
	public static final String Get_MISMATCHED_MEMBERSHIP_SAMPLING_FILES = "select * from membership where filed_path like 'M%'";
	
	public static final String Get_MISMATCHED_MEMBERSHIP_SAMPLING_FILES_BY_PACS_IDS = "select * from membership where filed_path like 'M%' and membership.pacs_id in (?1)";
	
	public static final String Get_MISMATCHED_DEPOSITS_SAMPLING_FILES_BY_PACS_IDS = "select * from deposits where filed_path like 'D%' and deposits.pacs_id in (?1)";
	
	public static final String Get_MISMATCHED_LENDING_SAMPLING_FILES_BY_PACS_IDS = "select * from lending where filed_path like 'L%' and lending.pacs_id in (?1)";
	
	
//	public static final String DCT_STATUS_COUNT = "Select \n" + 
//			"	states.id, states.state_code, states.state_name,dccb.id as 'dccb_id',dccbbr.id as 'dccb_br_id',\n" + 
//			"    A.fvr_completed,B.dct_login_count, B.dct_inprogress_count, B.dct_complete_count, B.pre_migration_progress_count, B.pre_migration_complete_count,\n" + 
//			"    B.date, B.as_on_date\n" + 
//			"    From (\n" + 
//			"    Select state_id,dccb_id,dccbbr_id,fvr_completed From pacs_progress_summary\n" + 
//			"    ) A left join (\n" + 
//			"    Select dccb_id, dccb_br_id, date, as_on_date,\n" + 
//			"    count(case when dct_login_status = 'Yes' then 1 else NULL end) as dct_login_count,\n" + 
//			"    count(case when status ='DCT inprogress' then 1 else NULL end) as  dct_inprogress_count ,\n" + 
//			"    count(case when status ='DCT Completed' then 1 else NULL end) as  dct_complete_count ,\n" + 
//			"	count( case when status='Premigration Inprogress' then 1 else NULL end) as  pre_migration_progress_count,\n" + 
//			"	count( case when status='Premigration Completed' then 1 else NULL end) as  pre_migration_complete_count\n" + 
//			"    From dct_pacs_status_summary group by dccb_id, dccb_br_id\n" + 
//			"    ) B on A.dccb_id = B.dccb_id and A.dccbbr_id = B.dccb_br_id\n" + 
//			"    left join dccb on A.dccb_id = dccb.id\n" + 
//			"    left join dccbbr on A.dccbbr_id = dccbbr.id\n" + 
//			"    left join states on A.state_id = states.id\n" + 
//			"    where as_on_date = ?1";
	
//	public static final String DCT_STATUS_COUNT_BY_PROJECT = "Select\n" + 
//			"states.id as state_id, states.state_code, states.state_name,dccb.id as 'dccb_id',dccbbr.id as 'dccb_br_id',\n" + 
//			"dccb.name as dccb_name , dccbbr.name dccb_br_name, \n"+
//			"B.dct_login_count, B.dct_inprogress_count, B.dct_complete_count, B.pre_migration_progress_count, B.pre_migration_complete_count,\n" + 
//			"B.date, B.as_on_date\n" + 
//			"From (\n" + 
//			"Select state_id,dccb_id,dccbbr_id From pacs_progress_summary\n" + 
//			") A left join (\n" + 
//			"Select dccb_id, dccb_br_id, date, as_on_date,pacs_internal_code,\n" + 
//			"count(case when dct_login_status = 'Yes' then 1 else NULL end) as dct_login_count,\n" + 
//			"count(case when status ='DCT inprogress' then 1 else NULL end) as  dct_inprogress_count ,\n" + 
//			"count(case when status ='DCT Completed' then 1 else NULL end) as  dct_complete_count ,\n" + 
//			"count( case when status='Premigration Inprogress' then 1 else NULL end) as  pre_migration_progress_count,\n" + 
//			"count( case when status='Premigration Completed' then 1 else NULL end) as  pre_migration_complete_count\n" + 
//			"From dct_pacs_status_summary group by dccb_id, dccb_br_id\n" + 
//			") B on A.dccb_id = B.dccb_id and A.dccbbr_id = B.dccb_br_id\n" + 
//			"left join dccb on A.dccb_id = dccb.id\n" + 
//			"left join dccbbr on A.dccbbr_id = dccbbr.id\n" + 
//			"left join states on A.state_id = states.id\n" + 
//			"where as_on_date = ?1  and pacs_internal_code in (?2);";
	
//	public static final String DCT_STATUS_COUNT_BY_PROJECT = "select\n" + 
//			"state_id, state_code, state_name, state_goi_code,\n" + 
//			"dccb_id, dccb_br_id, dccb_name, dccb_br_name,\n" + 
//			"count( case when dct_login_status = 'Yes' then 1 else NULL end) as  dct_login_count,\n" + 
//			"count( case when status='DCT inprogress' then 1 else NULL end) as  dct_in_progress_count,\n" + 
//			"count( case when status='DCT Completed' then 1 else NULL end) as  dct_complete_count,\n" + 
//			"count( case when status='Premigration Inprogress' then 1 else NULL end) as  pre_migration_progress_count,\n" + 
//			"count( case when status='Premigration Completed' then 1 else NULL end) as  pre_migration_complete_count,\n" + 
//			"date, as_on_date\n" + 
//			"from dct_pacs_status_summary\n" + 
//			"where as_on_date = ?1 and pacs_internal_code in (?2)\n" + 
//			"group by state_code";
	
	public static final String DCT_STATUS_COUNT = "select\n" + 
			"dccb_id, dccb_br_id,\n" + 
			"count( case when dct_login_status = 'Yes' then 1 else NULL end) as  dct_login_count,\n" + 
			"count( case when status='DCT inprogress' then 1 else NULL end) as  dct_in_progress_count,\n" + 
			"count( case when status='DCT Completed' then 1 else NULL end) as  dct_complete_count,\n" + 
			"count( case when status='Premigration Inprogress' then 1 else NULL end) as  pre_migration_progress_count,\n" + 
			"count( case when status='Premigration Completed' then 1 else NULL end) as  pre_migration_complete_count,\n" + 
			"count( case when status='Premigration Completed' then 1 else NULL end) as  pre_migration_complete_counts\n" + 
			"from dct_pacs_status_summary\n" + 
			"where as_on_date = ?1\n" + 
			"group by dccb_id, dccb_br_id";
	
	public static final String DCT_ERP_STATUS_COUNT = "select\n" + 
			"dccb_id, dccb_br_id,\n" + 
			"			count( case when login_status = 'Yes' then 1 else NULL end) as  dct_login_count, \n" +
			"			count( case when dct_in_progress_status='Yes' then 1 else NULL end) as  dct_in_progress_count, \n" + 
			"			count( case when dct_completed_status='Yes' then 1 else NULL end) as  dct_complete_count, \n" + 
			"			count( case when pre_migration_in_progress_status='Yes' then 1 else NULL end) as  pre_migration_progress_count,\n" + 
			"			count( case when pre_migration_completed_status='Yes' then 1 else NULL end) as  pre_migration_complete_count,\n" + 
			"			count( case when erp_login_status='Yes' then 1 else NULL end) as  erp_login_count,\n" + 
			"			count( case when erp_captured_status='Yes' then 1 else NULL end) as  erp_captured_count,\n" + 
			"			count( case when erp_live_status='Yes' then 1 else NULL end) as  erp_live_count,\n" + 
			"			count( case when erp_status4='Yes' then 1 else NULL end) as  erp_status_4_count,\n" + 
			"			count( case when erp_status5='Yes' then 1 else NULL end) as  erp_status_5_count\n" + 
			"from dct_pacs_status_summary\n" + 
			"where as_on_date = ?1\n" + 
			"group by dccb_id, dccb_br_id";
	
	public static final String Get_DCT_STATUS_SUMMARY_BY_AS_ON_DATE = "select dccb_id,sum(fvr_count) as fvr_count,sum(dct_login_count) as dct_login_count,"
			+ "sum(dct_in_progress_count) as dct_in_progress_count,sum(dct_complete_count) as dct_complete_count,\n" + 
			"sum(pre_migration_progress_count) as pre_migration_progress_count,sum(pre_migration_complete_count) as pre_migration_complete_count\n" + 
			"from dct_status_summary \n" + 
			"where as_on_date= ?1 \n" + 
			"group by dccb_id;";

	
}
