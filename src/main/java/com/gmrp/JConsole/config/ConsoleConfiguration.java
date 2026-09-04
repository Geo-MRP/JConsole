/* SPDX-License-Identifier: AGPL-3.0-or-later */

package com.gmrp.JConsole.config;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleConfiguration.class);
	private JSONObject configuration;

	public ConsoleConfiguration() {
		configuration = new JSONObject();
		// WIP
	}
}
