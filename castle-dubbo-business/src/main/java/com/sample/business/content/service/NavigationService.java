package com.sample.business.content.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castle.repo.domain.SortNoComparator;
import com.castle.repo.jpa.EntityService;
import com.sample.business.content.entity.NavigationEntity;
import com.sample.business.content.entity.NavigationEntity.Position;
import com.sample.business.content.repo.NavigationRepository;

@Service
public class NavigationService extends EntityService<NavigationEntity, Long> {

	@Autowired
	private NavigationRepository navigationRepository;

	public List<NavigationEntity> findByPosition(Position position) {
		List<NavigationEntity> navigationEntities = navigationRepository.findByPosition(position);
		Collections.sort(navigationEntities, SortNoComparator.COMPARATOR);
		return navigationEntities;
	}

	public void initData() {
		if (!(count() > 0)) {
			add("文章", "/article", false, 0);
		}
	}

	public void add(String name, String url, boolean blankTarget, Integer sortNo) {
		NavigationEntity navigation = initDomain();
		navigation.setName(name);
		navigation.setBlankTarget(blankTarget);
		navigation.setUrl(url);
		navigation.setSortNo(sortNo);
		save(navigation);
	}
}
