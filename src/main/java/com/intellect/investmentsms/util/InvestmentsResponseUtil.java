package com.intellect.investmentsms.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class InvestmentsResponseUtil {
	private static final Logger log = LoggerFactory.getLogger(InvestmentsResponseUtil.class);

	public static ResponseObject buildResponse(String status, String statusMsg, List<?> data) {
		// log.debug(RbacConstants.STARTING_METHOD+RbacCommonFunctionUtil.getCurrentMethodName());
		log.debug("Request status" + status);
		ResponseObject resObj = new ResponseObject();
		resObj.setStatus(status);
		if (null != status) {
			resObj.setStatus(status);
		}
		if (null != statusMsg) {
			resObj.setStatusMsg(statusMsg);
		} else {
			resObj.setStatusMsg(null);
		}
		if (null != data) {
			resObj.setData(data);
		}
		// log.debug(RbacConstants.ENDING_METHOD+RbacCommonFunctionUtil.getCurrentMethodName());
		return resObj;
	}
	
	 public static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) 
	    {
	      final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();
	       
	      return t -> 
	      {
	        final List<?> keys = Arrays.stream(keyExtractors)
	                    .map(ke -> ke.apply(t))
	                    .collect(Collectors.toList());
	         
	        return seen.putIfAbsent(keys, Boolean.TRUE) == null;
	      };
	    }
}
