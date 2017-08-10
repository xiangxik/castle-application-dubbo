package com.sample.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.castle.integration.core.ApplicationInitializer;
import com.sample.business.content.service.AdPositionService;
import com.sample.business.member.entity.AdminEntity;
import com.sample.business.member.repo.AdminRepository;

@Component
@Order(0)
public class ConsoleInitializer extends ApplicationInitializer {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdPositionService adPositionService;

	@Override
	public void onRootContextRefreshed() {
		if (adminRepository.count() == 0) {
			AdminEntity admin = adminRepository.initDomain();
			admin.setName("管理员");
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("asd123"));
			adminRepository.save(admin);
		}

		adPositionService.initData();

	}

}