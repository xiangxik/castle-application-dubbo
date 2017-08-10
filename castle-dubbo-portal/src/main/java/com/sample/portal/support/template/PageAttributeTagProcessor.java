package com.sample.portal.support.template;

import org.springframework.data.domain.Page;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

public class PageAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME = "page";
	private static final int PRECEDENCE = 10000;

	private String contextPath;

	protected PageAttributeTagProcessor(final String dialectPrefix, String contextPath) {
		super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
		this.contextPath = contextPath;
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {

		String url = contextPath + tag.getAttributeValue("url");

		final IStandardExpressionParser expressionParser = StandardExpressions
				.getExpressionParser(context.getConfiguration());
		final IStandardExpression expression = expressionParser.parseExpression(context, attributeValue);
		final Object value = expression.execute(context);

		StringBuilder builder = new StringBuilder();
		Page<?> page = (Page<?>) value;

		if (page.isFirst()) {
			builder.append("<li><a href=\"javascript:;\">上一页</a></li>");
		} else {
			builder.append("<li><a href=\"" + pageToUrl(url, 0) + "\">上一页</a></li>");
		}

		int number = page.getNumber();
		int totalPages = page.getTotalPages();

		if (totalPages > 0) {
			int left = number - 5 >= 0 ? number - 5 : 0;
			int right = number + 5 > totalPages ? totalPages : number + 5;
			for (int i = left; i < right; i++) {
				if (i == number) {
					builder.append("<li class=\"active\"><a href=\"javascript:;\">" + (i + 1) + "</a></li>");
				} else {
					builder.append("<li><a href=\"" + pageToUrl(url, i) + "\">" + (i + 1) + "</a></li>");
				}

			}
		}

		if (page.isLast()) {
			builder.append("<li><a href=\"javascript:;\">下一页</a></li>");
		} else {
			builder.append("<li><a href=\"" + pageToUrl(url, page.getTotalPages() - 1) + "\">上一页</a></li>");
		}

		structureHandler.setBody(builder.toString(), false);
	}

	private String pageToUrl(String url, Integer page) {
		if (url.contains("?")) {
			url = url + "&page=" + (page + 1);
		} else {
			url = url += "?page=" + (page + 1);
		}
		return url;
	}

}
