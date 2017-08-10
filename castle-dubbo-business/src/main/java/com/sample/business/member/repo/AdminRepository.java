package com.sample.business.member.repo;

import com.castle.repo.jpa.EntityRepository;
import com.sample.business.member.entity.AdminEntity;

public interface AdminRepository extends EntityRepository<AdminEntity, Long> {

	AdminEntity findByUsername(String username);
}
