package com.sample.portal.support.template;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import com.castle.core.SpringContext;
import com.sample.portal.support.template.expression.AuthencationExpression;
import com.sample.portal.support.template.expression.NavigationExpression;

public class PortalExpressionObjectFactory implements IExpressionObjectFactory {

	public static final String NAVIGATION_EXPRESSION_OBJECT_NAME = "navs";
	public static final String AUTHENCATION_EXPRESSION_OBJECT_NAME = "auths";

	protected static final Set<String> ALL_EXPRESSION_OBJECT_NAMES = Collections
			.unmodifiableSet(new LinkedHashSet<String>(java.util.Arrays
					.asList(new String[] { NAVIGATION_EXPRESSION_OBJECT_NAME, AUTHENCATION_EXPRESSION_OBJECT_NAME })));

	@Override
	public Set<String> getAllExpressionObjectNames() {
		return ALL_EXPRESSION_OBJECT_NAMES;
	}

	@Override
	public Object buildObject(IExpressionContext context, String expressionObjectName) {
		if (NAVIGATION_EXPRESSION_OBJECT_NAME.equals(expressionObjectName)) {
			return SpringContext.getBean(NavigationExpression.class);
		} else if (AUTHENCATION_EXPRESSION_OBJECT_NAME.equals(expressionObjectName)) {
			return SpringContext.getBean(AuthencationExpression.class);
		}
		return null;
	}

	@Override
	public boolean isCacheable(String expressionObjectName) {
		return true;
	}

}
