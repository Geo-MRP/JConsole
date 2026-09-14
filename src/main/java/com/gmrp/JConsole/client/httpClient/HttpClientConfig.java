/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import com.gmrp.JConsole.client.Client;
import com.gmrp.JConsole.client.ClientConfig;

import java.net.URI;
import java.time.Duration;

public class HttpClientConfig implements ClientConfig {
	private final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

	private URI mapEndpoint;
	private URI chatEndpoint;
	private int chatAuthUserId;
	private String chatAuthSessionId;
	private Duration timeout;

	@Override
	public String getType() {
		return "HTTP";
	}

	@Override
	public HttpClientConfig fromEnv() {
		if (!System.getenv().containsKey("CLIENT_HTTP_MAP_ENDPOINT"))
			throw new IllegalArgumentException("CLIENT_HTTP_MAP_ENDPOINT environment variable is not set");

		if (!System.getenv().containsKey("CLIENT_HTTP_CHAT_ENDPOINT"))
			throw new IllegalArgumentException("CLIENT_HTTP_CHAT_ENDPOINT environment variable is not set");

		if (!System.getenv().containsKey("CLIENT_HTTP_CHAT_AUTH_USER_ID"))
			throw new IllegalArgumentException("CLIENT_HTTP_CHAT_AUTH_USER_ID environment variable is not set");

		if (!System.getenv().containsKey("CLIENT_HTTP_CHAT_AUTH_SESSION_ID"))
			throw new IllegalArgumentException("CLIENT_HTTP_CHAT_AUTH_SESSION_ID environment variable is not set");

		mapEndpoint = URI.create(System.getenv("CLIENT_HTTP_MAP_ENDPOINT"));
		chatEndpoint = URI.create(System.getenv("CLIENT_HTTP_CHAT_ENDPOINT"));
		chatAuthSessionId = System.getenv("CLIENT_HTTP_CHAT_AUTH_SESSION_ID");
		chatAuthUserId = Integer.parseInt(System.getenv("CLIENT_HTTP_CHAT_AUTH_USER_ID"));
		timeout = Duration.parse(System.getenv().getOrDefault("CLIENT_HTTP_TIMEOUT", DEFAULT_TIMEOUT.toString()));
		return this;
	}

	@Override
	public Client createClient() {
		return new HttpClientBuilder()
				.withTimeout(timeout)
				.withMapEndpoint(mapEndpoint)
				.withChatEndpoint(chatEndpoint)
				.withChatAuthUserId(chatAuthUserId)
				.withChatAuthSessionId(chatAuthSessionId)
				.build();
	}
}