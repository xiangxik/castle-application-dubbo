package com.sample.dashboard.common.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.castle.security.CurrentUser;
import com.sample.business.member.entity.AdminEntity;
import com.sample.dashboard.security.AdminDetailsService.CurrentUserDetails;
import com.sample.dashboard.support.BaseController;

@Controller
@RequestMapping
public class CommonController extends BaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "/login";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboardPage(@CurrentUser AdminEntity currentUser, Model model) {
		return "/dashboard";
	}

	@RequestMapping(value = "/console", method = RequestMethod.GET)
	public String dashboardPage(@AuthenticationPrincipal CurrentUserDetails currentUserDetails, Model model) {
		model.addAttribute("currentUser", currentUserDetails == null ? null : currentUserDetails.getCustomUser());
		return "/console";
	}

}
