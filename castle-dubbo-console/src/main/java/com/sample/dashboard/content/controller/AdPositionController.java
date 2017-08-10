package com.sample.dashboard.content.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.core.types.Predicate;
import com.sample.business.content.entity.AdPositionEntity;
import com.sample.dashboard.support.EntityController;

@Controller
@RequestMapping("/content/adPosition")
public class AdPositionController extends EntityController<AdPositionEntity, Long> {

	@Override
	public Page<AdPositionEntity> doPage(Predicate predicate, Pageable pageable) {
		return super.doInternalPage(predicate, pageable);
	}

}
