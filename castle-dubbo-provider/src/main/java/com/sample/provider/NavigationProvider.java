package com.sample.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.api.NavigationApi;
import com.sample.business.content.entity.NavigationEntity;
import com.sample.business.content.entity.NavigationEntity.Position;
import com.sample.business.content.service.NavigationService;
import com.sample.domain.Navigation;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class NavigationProvider implements NavigationApi {

	@Autowired
	private NavigationService navigationService;

	@Override
	public List<Navigation> findByPosition(String position) {
		List<NavigationEntity> navigationEntities = navigationService.findByPosition(Position.valueOf(position));
		List<Navigation> navigations = new ArrayList<>();
		for (NavigationEntity navigationEntity : navigationEntities) {
			navigations.add(toDomain(navigationEntity));
		}
		return navigations;
	}

	protected Navigation toDomain(NavigationEntity entity) {
		Navigation domain = new Navigation();
		domain.setId(entity.getId());
		domain.setBlankTarget(entity.isBlankTarget());
		domain.setName(entity.getName());
		domain.setPosition(entity.getPosition().toString());
		domain.setUrl(entity.getUrl());
		return domain;
	}

}
