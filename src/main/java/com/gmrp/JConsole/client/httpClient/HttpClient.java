/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import java.net.URI;
import java.time.Duration;

public class HttpClient {
	private URI mapUri;
	private Duration timeout;

	public HttpClient(URI map_url, Duration timeout) {
		this.mapUri = map_url;
		this.timeout = timeout;
	}
}
