package com.sample.dashboard.member.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.core.types.Predicate;
import com.sample.business.member.entity.AdminEntity;
import com.sample.dashboard.support.EntityController;

@Controller
@RequestMapping("/member/admin")
public class AdminController extends EntityController<AdminEntity, Long> {

	@Override
	public Page<AdminEntity> doPage(Predicate predicate, Pageable pageable) {
		return super.doInternalPage(predicate, pageable);
	}

}
