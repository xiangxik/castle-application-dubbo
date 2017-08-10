package com.sample.business.content.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.castle.repo.jpa.EntityService;
import com.sample.business.content.entity.ArticleEntity;

@Service
public class ArticleService extends EntityService<ArticleEntity, Long> {

	@Override
	public <S extends ArticleEntity> S save(S entity) {
		if (entity.getPublishedDate() == null && entity.isPublished()) {
			entity.setPublishedDate(new Date());
		}
		return super.save(entity);
	}

}
