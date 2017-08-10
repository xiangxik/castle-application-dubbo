package com.sample.dashboard;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.castle.integration.webapp.FullBeanNameGenerator;
import com.castle.web.ServletSupport;

@Configuration
@ComponentScan(basePackages = { "com.sample" }, useDefaultFilters = false, includeFilters = {
		@Filter({ Controller.class }), @Filter({ ServletSupport.class }) }, nameGenerator = FullBeanNameGenerator.class)
@EnableWebMvc
public class SampleServletConfigBean {

}
