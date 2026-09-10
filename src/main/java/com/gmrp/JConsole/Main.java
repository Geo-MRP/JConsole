/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole;

import com.gmrp.JConsole.controller.Wrapper;
import com.gmrp.JConsole.controller.WrapperConfig;
import com.gmrp.JConsole.controller.WrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	static void main(String[] args) {
		WrapperConfig wrapperConfig = new WrapperConfig().fromEnv();
		Wrapper wrapper = WrapperFactory.create(wrapperConfig);
	}
}