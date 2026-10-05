/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmrp.JConsole.client.ClientBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.time.Duration;

public class HttpGmrpClientBuilder extends ClientBuilder {
	private URI mapUri;
	private URI chatUri;

	private int chatAuthUserId;
	private String chatAuthSessionId;

	private Duration timeout;

	public HttpGmrpClientBuilder withMapEndpoint(URI uri) {
		this.mapUri = uri;
		return this;
	}

	public HttpGmrpClientBuilder withChatEndpoint(URI uri) {
		this.chatUri = uri;
		return this;
	}

	public HttpGmrpClientBuilder withChatAuthUserId(int chatAuthUserId) {
		this.chatAuthUserId = chatAuthUserId;
		return this;
	}

	public HttpGmrpClientBuilder withChatAuthSessionId(String chatAuthSessionId) {
		this.chatAuthSessionId = chatAuthSessionId;
		return this;
	}

	public HttpGmrpClientBuilder withTimeout(Duration timeout) {
		this.timeout = timeout;
		return this;
	}

	public HttpGmrpClient build() {
		return new HttpGmrpClient(mapUri, chatUri, chatAuthUserId, chatAuthSessionId, timeout, HttpClient.newHttpClient(), new ObjectMapper());
	}
}
