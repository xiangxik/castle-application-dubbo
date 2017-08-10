package com.sample.business.content.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.castle.repo.jpa.DataEntity;
import com.sample.business.member.entity.AdminEntity;

@Entity
@Table(name = "tbl_article")
public class ArticleEntity extends DataEntity<AdminEntity, Long> {

	private static final long serialVersionUID = 2592900939574931917L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_category", nullable = false)
	private ArticleCategoryEntity category;

	@NotNull
	@Size(max = 100)
	@Column(nullable = false, length = 200)
	private String title;

	@NotNull
	@Size(max = 50)
	@Column(nullable = false, length = 100)
	private String author;

	@NotNull
	@Lob
	private String content;

	@Size(max = 500)
	@Column(length = 1000)
	private String summary;

	@Size(max = 200)
	@Column(length = 200)
	private String linkTo;

	@Size(max = 200)
	@Column(length = 200)
	private String thumbnail;

	@Column(nullable = false)
	private boolean published = false;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date publishedDate;

	@Column(nullable = false)
	private boolean stick = false;

	@Column(nullable = false)
	private long hits = 0l;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_article_to_tag")
	@OrderBy("sortNo asc")
	private Set<ArticleTagEntity> tags = new HashSet<>();

	/** 页面标题 */
	@Size(max = 200)
	@Column(length = 200)
	private String seoTitle;

	/** 页面关键词 */
	@Size(max = 200)
	@Column(length = 200)
	private String seoKeywords;

	/** 页面描述 */
	@Size(max = 200)
	@Column(length = 200)
	private String seoDescription;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLinkTo() {
		return linkTo;
	}

	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public ArticleCategoryEntity getCategory() {
		return category;
	}

	public void setCategory(ArticleCategoryEntity category) {
		this.category = category;
	}

	public boolean isStick() {
		return stick;
	}

	public void setStick(boolean stick) {
		this.stick = stick;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	public Set<ArticleTagEntity> getTags() {
		return tags;
	}

	public void setTags(Set<ArticleTagEntity> tags) {
		this.tags = tags;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

}
