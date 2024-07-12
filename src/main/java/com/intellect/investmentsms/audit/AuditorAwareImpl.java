package com.intellect.investmentsms.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.intellect.investmentsms.util.ApplicationConstants;

import jakarta.servlet.http.HttpServletRequest;




public class AuditorAwareImpl implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		String userId = ApplicationConstants.AUDITABLE_ADMIN_USER;
		HttpServletRequest request = null;
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			request = ((ServletRequestAttributes) requestAttributes).getRequest();
			if (request != null && null != request.getHeader(ApplicationConstants.HEADER_USERID)) {
				userId = request.getHeader(ApplicationConstants.HEADER_USERID);
			}
		}
		return Optional.ofNullable(Long.valueOf(userId));
	}

}
