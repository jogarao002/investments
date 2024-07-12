package com.intellect.investmentsms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonFunctionUtil {
	
	private static final Logger log = LoggerFactory.getLogger(CommonFunctionUtil.class);
	public static String getCurrentMethodName() {
		//log.debug(RbacConstants.STARTING_METHOD+RbacCommonFunctionUtil.getCurrentMethodName());
		StringBuilder sb=new StringBuilder();
		//sb.append("Class Name:");
		sb.append(Thread.currentThread().getStackTrace()[2].getClassName());
		sb.append("::");
		//sb.append("Method Name:");
		sb.append(Thread.currentThread().getStackTrace()[2].getMethodName());
		//log.debug(RbacConstants.ENDING_METHOD+RbacCommonFunctionUtil.getCurrentMethodName());
        return  sb.toString();
    }
	
	/**
	 * @author navya sree.
	 * @usage for  Date conversions to long type timestamp .
	 */
    public static Long getTimeStamp() {
		return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
	}
    
    public static Long getCurrentDateWithoutTime() {
    	
    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);
        
		return cal.getTimeInMillis();
	}

    /**
	 * @author navya sree.
	 * @usage generate token key by user name,current time .
	 */
    public static String getTokenKey(String email) {
    	StringBuilder tokenkey=new StringBuilder();
    	tokenkey.append(email);
    	tokenkey.append(getTimeStamp());
    	
		return tokenkey.toString();
	}
    
    public static byte[] convertStringToBlob(String input) {
    	byte[] byteConent=null;
		if(null!=input) {
    	 byteConent = input.getBytes();
		}
    	return byteConent;
    }
    
    public static String convertBlobToString(byte[] bdata) {
    	String result =null;
    	if(null!=bdata) {
		result= new String(bdata);
    	}
    	return result;
    }
    
	public static Long getTimeByDate(String date) throws Exception {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFormatter.parse(date));
		return cal.getTimeInMillis();
	}
	
	
	public static Boolean namePatternForOrganizations(String name) {
		
		Pattern pattern = Pattern.compile("^[^\\s][a-zA-Z0-9-_,@.&*#$ /?()\\s]*$");
	    Matcher matcher = pattern.matcher(name);
	    boolean matchFound = matcher.find();
	   
		return matchFound;
	}

	public static Boolean namePattern(String name) {
		
		Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z.\\s]*$");
	    Matcher matcher = pattern.matcher(name);
	    boolean matchFound = matcher.find();
	   
		return matchFound;
	}
	
	public static Boolean mobilePattern(String name) {
		
		Pattern pattern = Pattern.compile("^[6-9]{1}[0-9]{9}");
	    Matcher matcher = pattern.matcher(name);
	    boolean matchFound = matcher.find();
		
		return matchFound;
	}
	
	public static Boolean shortCodePattern(String code) {
		
		Pattern pattern = Pattern.compile("^[A-Za-z]{4}");
	    Matcher matcher = pattern.matcher(code);
	    boolean matchFound = matcher.find();
		
		return matchFound;
	}
	
	public static Boolean emailPattern(String email) {
		
		Pattern pattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
		Matcher matcher = pattern.matcher(email);
		boolean matchFound = matcher.find();

		return matchFound;
	}
	
	public static Boolean rbiCodePattern(String code) {

		Pattern pattern = Pattern.compile("^[A-Za-z0-9]{4}");
		Matcher matcher = pattern.matcher(code);
		boolean matchFound = matcher.find();

		return matchFound;
	}
	
	public static Map<Integer,String> collectionApportionDetailsList(){
		
		Map<Integer,String> collectionApportionDetailsList = new HashMap<Integer, String>();
		
		collectionApportionDetailsList.put(1, "Principal");
		collectionApportionDetailsList.put(2, "Interest Received");
		collectionApportionDetailsList.put(3, "Penal Interest");
		collectionApportionDetailsList.put(4, "Charges");
		collectionApportionDetailsList.put(5, "IOD");
		collectionApportionDetailsList.put(6, "LT Postponed Interest");
		collectionApportionDetailsList.put(7, "Moratorium Interest Adjusted");
		collectionApportionDetailsList.put(8, "Moratorium Interest Received-ST");
		collectionApportionDetailsList.put(9, "Others Collected-ST");
		collectionApportionDetailsList.put(10, "Moratorium Interest Received-KCC");
		collectionApportionDetailsList.put(11, "Others Collected-KCC");
		collectionApportionDetailsList.put(12, "Receivables");
		collectionApportionDetailsList.put(13, "Insurance");
		collectionApportionDetailsList.put(14, "Crop Insurance");
		collectionApportionDetailsList.put(15, "Arbitration Fees");
		collectionApportionDetailsList.put(16, "Due To Member");
		collectionApportionDetailsList.put(17, "Due By Member");
		
		return collectionApportionDetailsList;
		
	}
	
	public static Long getDayStrartByDate(long date) {

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		
		if(date != 0)
			cal.setTimeInMillis(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTimeInMillis();
	}

	public static Long getDayEndByDate(long date) {

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		
		if(date != 0)
			cal.setTimeInMillis(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTimeInMillis();
	}
    
	public static String generateRandomNumber() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		// Generate the first digit to ensure it's not 0
		sb.append(random.nextInt(9) + 1);

		// Generate the rest of the 9 digits
		for (int i = 0; i < 9; i++) {
			sb.append(random.nextInt(10));
		}

		return sb.toString();
	}
	
}
