package com.sample.business.content.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.castle.repo.jpa.SortEntity;
import com.sample.business.member.entity.AdminEntity;

@Entity
@Table(name = "tbl_navigation")
public class NavigationEntity extends SortEntity<AdminEntity, Long> {

	private static final long serialVersionUID = -3477412243870567483L;

	/**
	 * 位置
	 */
	public enum Position {

		/** 顶部 */
		top,

		/** 中间 */
		middle,

		/** 底部 */
		bottom
	}

	/** 名称 */
	@NotNull
	@Size(max = 200)
	@Column(nullable = false)
	private String name;

	/** 位置 */
	@Enumerated(EnumType.STRING)
	private Position position;

	/** 链接地址 */
	@NotNull
	@Size(max = 200)
	@Column(nullable = false)
	private String url;

	/** 是否新窗口打开 */
	private boolean blankTarget = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isBlankTarget() {
		return blankTarget;
	}

	public void setBlankTarget(boolean blankTarget) {
		this.blankTarget = blankTarget;
	}
}
