package com.sample.business.content.repo;

import com.castle.repo.jpa.EntityRepository;
import com.sample.business.content.entity.AdPositionEntity;

public interface AdPositionRepository extends EntityRepository<AdPositionEntity, Long> {

	AdPositionEntity findByCode(String code);
}
