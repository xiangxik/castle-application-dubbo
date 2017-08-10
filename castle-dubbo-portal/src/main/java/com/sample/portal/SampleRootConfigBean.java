package com.sample.portal;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.castle.web.ServletSupport;

@Configuration
@ComponentScan(basePackages = { "com.sample" }, excludeFilters = {
		@ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
		@ComponentScan.Filter(value = EnableWebMvc.class, type = FilterType.ANNOTATION),
		@ComponentScan.Filter(value = ServletSupport.class, type = FilterType.ANNOTATION) })
public class SampleRootConfigBean {

}
