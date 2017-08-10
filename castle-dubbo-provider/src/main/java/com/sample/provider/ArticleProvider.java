package com.sample.provider;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sample.api.ArticleApi;
import com.sample.business.content.entity.ArticleCategoryEntity;
import com.sample.business.content.entity.ArticleEntity;
import com.sample.business.content.entity.ArticleTagEntity;
import com.sample.business.content.entity.QArticleEntity;
import com.sample.business.content.service.ArticleService;
import com.sample.domain.Article;
import com.sample.domain.ArticleCategory;
import com.sample.domain.ArticleTag;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class ArticleProvider implements ArticleApi {

	@Autowired
	private ArticleService articleService;

	@Override
	public Page<Article> findAll(Pageable pageable) {
		return articleService.findAll(QArticleEntity.articleEntity.published.isTrue(), pageable)
				.map(this::toSimpleDomain);
	}

	@Override
	public Article findOne(Long id) {
		return toDetailDomain(articleService.findOne(id));
	}

	public Article toSimpleDomain(ArticleEntity entity) {
		Article domain = new Article();
		domain.setId(entity.getId());
		domain.setAuthor(entity.getAuthor());
		domain.setHits(entity.getHits());
		domain.setLinkTo(entity.getLinkTo());
		domain.setPublishedDate(entity.getPublishedDate());
		domain.setStick(entity.isStick());
		domain.setSummary(entity.getSummary());
		domain.setThumbnail(entity.getThumbnail());
		domain.setTitle(entity.getTitle());

		ArticleCategoryEntity categoryEntity = entity.getCategory();
		ArticleCategory category = new ArticleCategory();
		category.setId(categoryEntity.getId());
		category.setName(categoryEntity.getName());
		domain.setCategory(category);

		Set<ArticleTagEntity> tagEntities = entity.getTags();
		Set<ArticleTag> tags = new HashSet<>();
		for (ArticleTagEntity tagEntity : tagEntities) {
			ArticleTag tag = new ArticleTag();
			tag.setId(tagEntity.getId());
			tag.setName(tagEntity.getName());
			tags.add(tag);
		}
		domain.setTags(tags);

		return domain;
	}

	public Article toDetailDomain(ArticleEntity entity) {
		Article domain = toSimpleDomain(entity);
		domain.setContent(entity.getContent());
		domain.setSeoDescription(entity.getSeoDescription());
		domain.setSeoKeywords(entity.getSeoKeywords());
		domain.setSeoTitle(entity.getSeoTitle());
		return domain;
	}
}
