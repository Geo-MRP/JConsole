/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import java.net.URI;
import java.time.Duration;

public class HttpClientBuilder {
	private URI mapUri;
	private URI chatUri;

	private int chatAuthUserId;
	private String chatAuthSessionId;

	private Duration timeout;

	public HttpClientBuilder withMapEndpoint(URI uri) {
		this.mapUri = uri;
		return this;
	}

	public HttpClientBuilder withChatEndpoint(URI uri) {
		this.chatUri = uri;
		return this;
	}

	public HttpClientBuilder withChatAuthUserId(int chatAuthUserId) {
		this.chatAuthUserId = chatAuthUserId;
		return this;
	}

	public HttpClientBuilder withChatAuthSessionId(String chatAuthSessionId) {
		this.chatAuthSessionId = chatAuthSessionId;
		return this;
	}

	public HttpClientBuilder withTimeout(Duration timeout) {
		this.timeout = timeout;
		return this;
	}

	public HttpClient build() {
		return new HttpClient(mapUri, chatUri, chatAuthUserId, chatAuthSessionId, timeout);
	}
}
