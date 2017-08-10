package com.sample.dashboard.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.core.types.Predicate;
import com.sample.business.content.entity.AdEntity;
import com.sample.business.content.service.AdPositionService;
import com.sample.dashboard.support.EntityController;

@Controller
@RequestMapping("/content/ad")
public class AdController extends EntityController<AdEntity, Long> {

	@Autowired
	private AdPositionService adPositionService;
	
	@Override
	public Page<AdEntity> doPage(Predicate predicate, Pageable pageable) {
		return super.doInternalPage(predicate, pageable);
	}

	@Override
	protected void onShowEditPage(AdEntity entity, Model model) {
		super.onShowEditPage(entity, model);
		
		model.addAttribute("adPositions", adPositionService.findAll());
	}
}
