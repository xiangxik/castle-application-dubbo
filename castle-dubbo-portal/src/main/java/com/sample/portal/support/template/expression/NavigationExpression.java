package com.sample.portal.support.template.expression;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sample.api.NavigationApi;
import com.sample.domain.Navigation;

@Component
public class NavigationExpression {

	@Reference
	private NavigationApi navigationApi;

	public List<Navigation> get(String position) {
		return navigationApi.findByPosition(position);
	}

}
