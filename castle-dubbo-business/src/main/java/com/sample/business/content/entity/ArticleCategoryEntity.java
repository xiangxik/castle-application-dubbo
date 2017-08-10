package com.sample.business.content.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.castle.repo.jpa.HierarchicalEntity;
import com.sample.business.member.entity.AdminEntity;

@Entity
@Table(name = "tbl_article_category")
public class ArticleCategoryEntity extends HierarchicalEntity<AdminEntity, Long, ArticleCategoryEntity> {

	private static final long serialVersionUID = -1184664942624917482L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
