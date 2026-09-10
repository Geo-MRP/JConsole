/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import java.net.URI;
import java.time.Duration;

public class HttpClientBuilder {
	private URI mapUri;
	private Duration timeout;

	public HttpClientBuilder mapUri(URI mapUri) {
		this.mapUri = mapUri;
		return this;
	}

	public HttpClientBuilder timeout(Duration timeout) {
		this.timeout = timeout;
		return this;
	}

	public HttpClient build() {
		return new HttpClient(mapUri, timeout);
	}
}
