/* SPDX-License-Identifier: AGPL-3.0-or-later */

package com.gmrp.JConsole;

import com.gmrp.JConsole.client.Client;
import com.gmrp.JConsole.config.ConsoleConfiguration;
import com.gmrp.JConsole.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	private static final ConsoleConfiguration CONFIGURATION = new ConsoleConfiguration();

	private static Client client;
	private static Service service;

	static void main(String[] args) {

	}
}
