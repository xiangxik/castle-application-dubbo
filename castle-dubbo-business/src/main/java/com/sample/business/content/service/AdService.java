package com.sample.business.content.service;

import org.springframework.stereotype.Service;

import com.castle.repo.jpa.EntityService;
import com.sample.business.content.entity.AdEntity;

@Service
public class AdService extends EntityService<AdEntity, Long> {

}
