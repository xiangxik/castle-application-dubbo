package com.sample.portal.support.dubbo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.castle.web.ServletSupport;

@Configuration
@ServletSupport
public class DubboConsumerServletConfigBean {

	@Bean
	public static AnnotationBean annotationBean() {
		AnnotationBean annotationBean = new AnnotationBean();
		return annotationBean;
	}
}
