package com.sample.provider;

import java.util.Properties;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.castle.integration.dubbo.javaconfig.JavaConfigContainer;

public class DubboProviderMain {

	public static void main(String[] args) throws Exception {

		String[] customArgs = new String[] { "javaconfig" };
		Properties properties = new Properties();
		properties.setProperty(JavaConfigContainer.SPRING_JAVACONFIG, "com.castle,com.sample");
		ConfigUtils.setProperties(properties);
		com.alibaba.dubbo.container.Main.main(customArgs);
	}
}
