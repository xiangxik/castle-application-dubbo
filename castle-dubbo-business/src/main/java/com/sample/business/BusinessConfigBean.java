package com.sample.business;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.castle.repo.jpa.EntityRepository;
import com.castle.repo.jpa.EntityRepositoryFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = { "com.sample.business" }, includeFilters = {
		@Filter(value = EntityRepository.class, type = FilterType.ASSIGNABLE_TYPE) }, repositoryImplementationPostfix = "Impl", repositoryFactoryBeanClass = EntityRepositoryFactoryBean.class)
public class BusinessConfigBean {

}
