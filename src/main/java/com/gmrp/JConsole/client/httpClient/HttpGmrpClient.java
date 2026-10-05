/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.gmrp.JConsole.client.Client;
import com.gmrp.JConsole.client.httpClient.dto.HttpMapResponse;
import com.gmrp.JConsole.client.httpClient.mapper.HttpUserMapper;
import com.gmrp.JConsole.model.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HttpGmrpClient implements Client {
	private URI mapUri;
	private URI chatUri;
	private String chatAuthSessionId;
	private int chatAuthUserId;
	private Duration timeout;

	private static final HttpClient CLIENT = HttpClient.newHttpClient();
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public HttpGmrpClient(URI mapUri, URI chatUri, int chatAuthUserId, String chatAuthSessionId, Duration timeout) {
		this.mapUri = mapUri;
		this.chatUri = chatUri;
		this.timeout = timeout;
		this.chatAuthUserId = chatAuthUserId;
		this.chatAuthSessionId = chatAuthSessionId;
	}

	public CompletableFuture<List<User>> getUsers() {
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(mapUri)
				.GET()
				.build();

		return CLIENT.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenApply(jsonBody -> {
					try {
						HttpMapResponse httpMapResponse = MAPPER.readValue(jsonBody, HttpMapResponse.class);
						if (httpMapResponse.users() == null) {
							return List.<User>of();
						}
						return httpMapResponse.users().stream()
								.map(HttpUserMapper::toDomain)
								.toList();
					} catch (JsonProcessingException e) {
						throw new RuntimeException("Failed to parse JSON response", e);
					}
				});
	}

}