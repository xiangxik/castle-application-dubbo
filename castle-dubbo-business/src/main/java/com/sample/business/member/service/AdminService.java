package com.sample.business.member.service;

import org.springframework.stereotype.Service;

import com.castle.repo.jpa.EntityService;
import com.sample.business.member.entity.AdminEntity;

@Service
public class AdminService extends EntityService<AdminEntity, Long> {

}
