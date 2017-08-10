package com.sample.business.content.repo;

import com.castle.repo.jpa.HierarchicalEntityRepository;
import com.sample.business.content.entity.ArticleCategoryEntity;

public interface ArticleCategoryRepository extends HierarchicalEntityRepository<ArticleCategoryEntity, Long> {

}
