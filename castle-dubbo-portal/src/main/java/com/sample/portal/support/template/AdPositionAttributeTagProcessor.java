package com.sample.portal.support.template;

import org.thymeleaf.context.Context;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;

import com.sample.api.AdPositionApi;
import com.sample.domain.AdPosition;

public class AdPositionAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private AdPositionApi adPositionApi;

	private static final String ATTR_NAME = "adPosition";
	private static final int PRECEDENCE = 10000;
	private SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();

	protected AdPositionAttributeTagProcessor(final String dialectPrefix, AdPositionApi adPositionApi) {
		super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
		this.adPositionApi = adPositionApi;
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {

		AdPosition adPosition = adPositionApi.findByCode(attributeValue);
		if (adPosition == null) {
			structureHandler.setBody("", false);
		} else {
			Context tempContext = new Context();
			tempContext.setVariable("pos", adPosition);
			tempContext.setVariable("ads", adPosition.getAds());
			structureHandler.setBody(springTemplateEngine.process(adPosition.getTemplate(), tempContext), true);
		}
	}

}
