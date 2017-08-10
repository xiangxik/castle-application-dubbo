package com.sample.business.content.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.castle.repo.jpa.DataEntity;
import com.sample.business.member.entity.AdminEntity;

@Entity
@Table(name = "tbl_ad_position")
public class AdPositionEntity extends DataEntity<AdminEntity, Long> {

	private static final long serialVersionUID = 2219800220502609042L;

	/** 名称 */
	@NotNull
	@Size(max = 200)
	@Column(nullable = false)
	private String name;

	/** 代号 */
	@NotNull
	@Size(max = 200)
	@Column(nullable = false, unique = true)
	private String code;

	/** 宽度 */
	@NotNull
	@Column(nullable = false)
	private Integer width;

	/** 高度 */
	@NotNull
	@Column(nullable = false)
	private Integer height;

	/** 描述 */
	@Size(max = 200)
	private String description;

	/** 模板 */
	@NotNull
	@Lob
	@Column(nullable = false)
	private String template;

	@OneToMany(mappedBy = "adPosition", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("sortNo asc")
	private Set<AdEntity> ads = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Set<AdEntity> getAds() {
		return ads;
	}

	public void setAds(Set<AdEntity> ads) {
		this.ads = ads;
	}

}
