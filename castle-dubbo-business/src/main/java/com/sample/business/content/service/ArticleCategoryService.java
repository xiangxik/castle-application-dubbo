package com.sample.business.content.service;

import org.springframework.stereotype.Service;

import com.castle.repo.jpa.HierarchicalEntityService;
import com.sample.business.content.entity.ArticleCategoryEntity;

@Service
public class ArticleCategoryService extends HierarchicalEntityService<ArticleCategoryEntity, Long> {

}
