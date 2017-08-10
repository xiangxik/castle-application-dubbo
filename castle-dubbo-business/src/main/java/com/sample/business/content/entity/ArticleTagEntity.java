package com.sample.business.content.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.castle.repo.jpa.SortEntity;
import com.sample.business.member.entity.AdminEntity;

@Entity
@Table(name = "tbl_article_tag")
public class ArticleTagEntity extends SortEntity<AdminEntity, Long> {

	private static final long serialVersionUID = 4594876293662572521L;

	@NotNull
	@Size(max = 100)
	@Column(nullable = false)
	private String name;

	@Size(max = 200)
	private String icon;

	@Size(max = 200)
	private String memo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
