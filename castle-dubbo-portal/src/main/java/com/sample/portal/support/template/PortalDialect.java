package com.sample.portal.support.template;

import java.util.Set;

import javax.servlet.ServletContext;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;

import com.google.common.collect.Sets;
import com.sample.api.AdPositionApi;

public class PortalDialect extends AbstractProcessorDialect implements IExpressionObjectDialect {

	private IExpressionObjectFactory expressionObjectFactory = null;

	private ServletContext servletContext;
	private AdPositionApi adPositionApi;

	public PortalDialect(AdPositionApi adPositionApi, ServletContext servletContext) {
		super("Portal Dialect", "portal", SpringStandardDialect.PROCESSOR_PRECEDENCE - 1);
		this.servletContext = servletContext;
		this.adPositionApi = adPositionApi;
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		return Sets.newHashSet(new AdPositionAttributeTagProcessor(dialectPrefix, adPositionApi),
				new PageAttributeTagProcessor(dialectPrefix, servletContext.getContextPath()));
	}

	@Override
	public IExpressionObjectFactory getExpressionObjectFactory() {
		if (this.expressionObjectFactory == null) {
			this.expressionObjectFactory = new PortalExpressionObjectFactory();
		}
		return this.expressionObjectFactory;
	}

}
