package com.sample.provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.api.AdPositionApi;
import com.sample.business.content.entity.AdEntity;
import com.sample.business.content.entity.AdPositionEntity;
import com.sample.business.content.service.AdPositionService;
import com.sample.domain.Ad;
import com.sample.domain.AdPosition;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class AdPositionProvider implements AdPositionApi {

	@Autowired
	private AdPositionService adPositionService;

	@Override
	public AdPosition findByCode(String code) {
		AdPositionEntity adPositionEntity = adPositionService.findByCode(code);
		return toDomain(adPositionEntity);
	}

	protected AdPosition toDomain(AdPositionEntity entity) {
		AdPosition domain = new AdPosition();
		domain.setCode(entity.getCode());
		domain.setDescription(entity.getDescription());
		domain.setHeight(entity.getHeight());
		domain.setId(entity.getId());
		domain.setName(entity.getName());
		domain.setTemplate(entity.getTemplate());
		domain.setWidth(entity.getWidth());

		for (AdEntity adEntity : entity.getAds()) {
			Ad ad = new Ad();
			ad.setId(adEntity.getId());
			ad.setBeginDate(adEntity.getBeginDate());
			ad.setContent(adEntity.getContent());
			ad.setEndDate(adEntity.getEndDate());
			ad.setPath(adEntity.getPath());
			ad.setTitle(adEntity.getTitle());
			ad.setType(adEntity.getType().toString());
			ad.setUrl(adEntity.getUrl());
			domain.getAds().add(ad);
		}

		return domain;
	}

}
