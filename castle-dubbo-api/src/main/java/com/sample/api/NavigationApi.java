package com.sample.api;

import java.util.List;

import com.sample.domain.Navigation;

public interface NavigationApi {

	List<Navigation> findByPosition(String position);
}
