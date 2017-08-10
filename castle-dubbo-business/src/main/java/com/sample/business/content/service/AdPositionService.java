package com.sample.business.content.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castle.repo.jpa.EntityService;
import com.sample.business.content.entity.AdPositionEntity;
import com.sample.business.content.repo.AdPositionRepository;

@Service
public class AdPositionService extends EntityService<AdPositionEntity, Long> {

	@Autowired
	private AdPositionRepository adPositionRepository;

	public AdPositionEntity findByCode(String code) {
		return adPositionRepository.findByCode(code);
	}

	public void initData() {
		if (!(count() > 0)) {
			add("首页轮播图", "index_carousel", 800, 300, "首页轮播图");
			add("首页侧栏", "index_sidebar", 400, 300, "首页侧栏广告");
		}
	}

	protected void add(String name, String code, Integer width, Integer height, String description) {
		AdPositionEntity adPosition = initDomain();
		adPosition.setName(name);
		adPosition.setCode(code);
		adPosition.setWidth(width);
		adPosition.setHeight(height);
		adPosition.setDescription(description);
		adPosition.setTemplate("--waiting--");
		save(adPosition);
	}
}
