package com.sample.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.castle.core.CastlePatterns;

public class User extends Domain<Long> {

	private static final long serialVersionUID = 5986273140241519968L;

	/** 账号 */
	@NotNull
	private String username;

	/** 姓名 */
	@NotNull
	@Size(max = 100)
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

}
