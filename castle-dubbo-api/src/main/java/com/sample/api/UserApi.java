package com.sample.api;

import com.sample.domain.User;

public interface UserApi {

	User findByUsername(String username);

	void register(User user, String encodedPassword);
	
	String getUserPassword(Long id);
	
	User findOne(Long id);
}
