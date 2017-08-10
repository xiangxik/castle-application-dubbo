package com.sample.business.content.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.castle.repo.jpa.SortEntity;
import com.sample.business.member.entity.AdminEntity;

@Entity
@Table(name = "tbl_ad")
public class AdEntity extends SortEntity<AdminEntity, Long> {
	private static final long serialVersionUID = -6637465088582821129L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 文本 */
		text,

		/** 图片 */
		image,

		/** 视频 */
		video,

		/** 音乐 */
		music
	}

	/** 标题 */
	@NotNull
	@Size(max = 200)
	@Column(nullable = false)
	private String title;

	/** 类型 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(nullable = false)
	private Type type;

	/** 内容 */
	@Lob
	private String content;

	/** 路径 */
	@Size(max = 200)
	private String path;

	/** 起始日期 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate;

	/** 结束日期 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	/** 链接地址 */
	@Size(max = 200)
	private String url;

	/** 广告位 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private AdPositionEntity adPosition;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public AdPositionEntity getAdPosition() {
		return adPosition;
	}

	public void setAdPosition(AdPositionEntity adPosition) {
		this.adPosition = adPosition;
	}

	/**
	 * 判断是否已开始
	 * 
	 * @return 是否已开始
	 */
	@Transient
	public boolean hasBegun() {
		return getBeginDate() == null || new Date().after(getBeginDate());
	}

	/**
	 * 判断是否已结束
	 * 
	 * @return 是否已结束
	 */
	@Transient
	public boolean hasEnded() {
		return getEndDate() != null && new Date().after(getEndDate());
	}

}
