package com.sample.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sample.domain.Article;

public interface ArticleApi {

	Page<Article> findAll(Pageable pageable);

	Article findOne(Long id);
}
