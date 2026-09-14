/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import com.gmrp.JConsole.client.Client;

import java.net.URI;
import java.time.Duration;

public class HttpClient implements Client {
	private URI mapUri;
	private URI chatUri;
	private String chatAuthSessionId;
	private int chatAuthUserId;
	private Duration timeout;

	public HttpClient(URI mapUri, URI chatUri, int chatAuthUserId, String chatAuthSessionId, Duration timeout) {
		this.mapUri = mapUri;
		this.chatUri = chatUri;
		this.timeout = timeout;
		this.chatAuthUserId = chatAuthUserId;
		this.chatAuthSessionId = chatAuthSessionId;
	}

}
/*
 * { "userCount":1319, "users":[ { "st":{ "lv":{ "ac_path":"a350", "idx":10134 }
 * }, }, ]
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * }
 */