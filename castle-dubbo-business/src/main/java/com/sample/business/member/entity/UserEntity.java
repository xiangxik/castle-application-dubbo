package com.sample.business.member.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.castle.core.CastlePatterns;
import com.castle.repo.domain.Disabledable;
import com.castle.repo.domain.Lockedable;
import com.castle.repo.domain.LogicDeleteable;
import com.castle.repo.jpa.DataEntity;

@Entity
@Table(name = "tbl_user")
public class UserEntity extends DataEntity<AdminEntity, Long> implements LogicDeleteable, Lockedable, Disabledable {

	private static final long serialVersionUID = -516809730581829159L;

	/** 账号 */
	@NotNull
	@Column(nullable = false, updatable = false, unique = true, length = 100)
	private String username;

	/** 密码 */
	@NotNull
	@Column(nullable = false, length = 200)
	private String password;

	/** 姓名 */
	@NotNull
	@Size(max = 100)
	@Column(nullable = false, length = 200)
	private String name;

	/** 头像 */
	private String avatar;

	/** E-mail */
	@Size(max = 200)
	@Pattern(regexp = CastlePatterns.REGEX_MAIL)
	private String email;

	/** 手机号码 */
	@Size(max = 200)
	@Pattern(regexp = CastlePatterns.REGEX_MOBILE)
	private String mobile;

	/** 是否禁用 */
	@Column(nullable = false)
	private boolean disabled = false;

	/** 是否锁定 */
	@Column(nullable = false)
	private boolean locked = false;

	/** 是否删除 */
	@Column(nullable = false)
	private boolean deleted = false;

	/** 连续登录失败次数 */
	@Column(nullable = false)
	private int loginFailureCount = 0;

	/** 锁定日期 */
	private Date lockedDate;

	/** 最后登录日期 */
	private Date lastLoginDate;

	/** 最后登录IP */
	private String lastLoginIp;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(int loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Override
	public boolean isLocked() {
		return locked;
	}

	@Override
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public void markLocked() {
		this.locked = true;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public void markDeleted() {
		this.deleted = true;
	}

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public void markDisabled() {
		this.disabled = true;
	}

}
