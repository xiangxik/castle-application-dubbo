package com.sample.business.content.repo;

import com.castle.repo.jpa.EntityRepository;
import com.sample.business.content.entity.ArticleEntity;

public interface ArticleRepository extends EntityRepository<ArticleEntity, Long> {

}
