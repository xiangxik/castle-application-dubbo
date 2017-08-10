package com.sample.api;

import com.sample.domain.AdPosition;

public interface AdPositionApi {

	AdPosition findByCode(String code);
}
