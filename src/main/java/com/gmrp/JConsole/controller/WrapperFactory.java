/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.controller;

import com.gmrp.JConsole.client.Client;
import com.gmrp.JConsole.service.Service;

public class WrapperFactory {
	public static Wrapper create(WrapperConfig wrapperConfig) {
		Client client = wrapperConfig.getClientConfig().createClient();
		Service service = wrapperConfig.getServiceConfig().createService();
		return new WrapperBuilder()
				.withClient(client)
				.withService(service)
				.build();
	}
}