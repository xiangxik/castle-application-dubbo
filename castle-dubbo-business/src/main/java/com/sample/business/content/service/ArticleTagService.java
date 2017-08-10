package com.sample.business.content.service;

import org.springframework.stereotype.Service;

import com.castle.repo.jpa.EntityService;
import com.sample.business.content.entity.ArticleTagEntity;

@Service
public class ArticleTagService extends EntityService<ArticleTagEntity, Long> {

}
