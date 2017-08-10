package com.sample.portal.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sample.api.ArticleApi;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Reference
	private ArticleApi articleApi;

	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public String show(
			@PageableDefault(size = 10, sort = "publishedDate", direction = Direction.DESC) Pageable pageable,
			Model model) {
		model.addAttribute("articles", articleApi.findAll(pageable));
		return "/article_list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("article", articleApi.findOne(id));
		return "/article_detail";
	}
}
