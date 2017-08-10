package com.sample.business.member.repo;

import com.castle.repo.jpa.EntityRepository;
import com.sample.business.member.entity.UserEntity;

public interface UserRepository extends EntityRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);
}
