/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import com.gmrp.JConsole.client.Client;
import com.gmrp.JConsole.client.ClientConfig;

public class HttpClientConfig implements ClientConfig {

	@Override
	public String getType() {
		return "HTTP";
	}

	@Override
	public HttpClientConfig fromEnv() {
		return this;
	}

	@Override
	public Client createClient() {
		return null;
	}
}