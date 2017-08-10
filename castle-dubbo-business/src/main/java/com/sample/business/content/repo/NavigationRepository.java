package com.sample.business.content.repo;

import java.util.List;

import com.castle.repo.jpa.EntityRepository;
import com.sample.business.content.entity.NavigationEntity;
import com.sample.business.content.entity.NavigationEntity.Position;

public interface NavigationRepository extends EntityRepository<NavigationEntity, Long> {

	List<NavigationEntity> findByPosition(Position position);
}
