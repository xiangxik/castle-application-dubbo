package com.sample.provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.api.UserApi;
import com.sample.business.member.entity.UserEntity;
import com.sample.business.member.service.UserService;
import com.sample.domain.User;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class UserProvider implements UserApi {

	@Autowired
	private UserService userService;

	@Override
	public User findByUsername(String username) {
		return toDomain(userService.findByUsername(username));
	}

	@Override
	public void register(User user, String encodedPassword) {
		UserEntity entity = toEntity(user);
		entity.setPassword(encodedPassword);
		userService.save(entity);
	}

	@Override
	public String getUserPassword(Long id) {
		UserEntity userEntity = userService.findOne(id);
		return userEntity.getPassword();
	}

	@Override
	public User findOne(Long id) {
		UserEntity userEntity = userService.findOne(id);
		return toDomain(userEntity);
	}

	protected UserEntity toEntity(User domain) {
		UserEntity entity = domain.getId() == null ? userService.initDomain() : userService.findOne(domain.getId());
		entity.setUsername(domain.getUsername());
		entity.setName(domain.getName());
		entity.setEmail(domain.getEmail());
		entity.setMobile(domain.getMobile());
		entity.setAvatar(domain.getAvatar());
		return entity;
	}

	protected User toDomain(UserEntity entity) {
		if (entity == null) {
			return null;
		}

		User user = new User();
		user.setId(entity.getId());
		user.setUsername(entity.getUsername());
		user.setName(entity.getName());
		user.setEmail(entity.getEmail());
		user.setMobile(entity.getMobile());
		user.setAvatar(entity.getAvatar());

		return user;
	}

}
