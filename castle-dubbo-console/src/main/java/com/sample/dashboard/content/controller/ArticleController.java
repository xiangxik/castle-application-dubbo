package com.sample.dashboard.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.core.types.Predicate;
import com.sample.business.content.entity.ArticleEntity;
import com.sample.business.content.service.ArticleTagService;
import com.sample.dashboard.support.EntityController;

@Controller
@RequestMapping("/content/article")
public class ArticleController extends EntityController<ArticleEntity, Long> {

	@Autowired
	private ArticleTagService articleTagService;

	@Override
	public Page<ArticleEntity> doPage(Predicate predicate, Pageable pageable) {
		return super.doInternalPage(predicate, pageable);
	}

	@Override
	protected void onShowEditPage(ArticleEntity entity, Model model) {
		super.onShowEditPage(entity, model);

		model.addAttribute("tags", articleTagService.findAll());
	}

}
