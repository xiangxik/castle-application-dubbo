package com.sample.portal.support.template.expression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.domain.User;
import com.sample.portal.security.AuthenticationAuditorAware;

@Component
public class AuthencationExpression {

	@Autowired
	private AuthenticationAuditorAware authenticationAuditorAware;

	public User getCurrentUser() {
		return authenticationAuditorAware.getCurrentAuditor();
	}

}
