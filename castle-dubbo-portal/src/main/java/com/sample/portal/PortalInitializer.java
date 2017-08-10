package com.sample.portal;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.alibaba.dubbo.config.annotation.Reference;
import com.castle.integration.core.ApplicationInitializer;
import com.sample.api.AdPositionApi;
import com.sample.portal.support.template.PortalDialect;

@Component
public class PortalInitializer extends ApplicationInitializer {

	@Autowired
	private SpringTemplateEngine springTemplateEngine;

	@Reference
	private AdPositionApi adPositionApi;

	@Autowired
	private ServletContext servletContext;

	@Override
	public void onServletContextRefreshed() {
		super.onServletContextRefreshed();

		springTemplateEngine.addDialect(new PortalDialect(adPositionApi, servletContext));
	}
}
