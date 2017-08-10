package com.sample.business.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castle.repo.jpa.EntityService;
import com.sample.business.member.entity.UserEntity;
import com.sample.business.member.repo.UserRepository;

@Service
public class UserService extends EntityService<UserEntity, Long> {

	@Autowired
	private UserRepository userRepository;

	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
