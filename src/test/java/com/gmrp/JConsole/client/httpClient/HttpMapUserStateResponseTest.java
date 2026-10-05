/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmrp.JConsole.client.httpClient.dto.HttpMapResponse;
import com.gmrp.JConsole.client.httpClient.dto.HttpMapUserStateResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class HttpMapUserStateResponseTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void shouldDeserializeStringLivery() throws Exception {
		String json = "{\"gr\":1,\"as\":8,\"lv\":\"3\"}";
		HttpMapUserStateResponse state = objectMapper.readValue(json, HttpMapUserStateResponse.class);

		assertNotNull(state);
		assertEquals(1, state.gr());
		assertEquals(8, state.as());
		assertNotNull(state.lv());
		assertNull(state.lv().ac_path());
		assertEquals(3, state.lv().idx());
	}

	@Test
	void shouldDeserializeMissingLivery() throws Exception {
		String json = "{\"gr\":1,\"as\":0}";
		HttpMapUserStateResponse state = objectMapper.readValue(json, HttpMapUserStateResponse.class);

		assertNotNull(state);
		assertEquals(1, state.gr());
		assertEquals(0, state.as());
		assertNull(state.lv());
	}

	@Test
	void shouldDeserializeObjectLivery() throws Exception {
		String json = "{\"gr\":1,\"as\":120,\"lv\":{\"ac_path\":\"a350\",\"idx\":10071}}";
		HttpMapUserStateResponse state = objectMapper.readValue(json, HttpMapUserStateResponse.class);

		assertNotNull(state);
		assertEquals(1, state.gr());
		assertEquals(120, state.as());
		assertNotNull(state.lv());
		assertEquals("a350", state.lv().ac_path());
		assertEquals("a350", state.lv().acPath());
		assertEquals(10071, state.lv().idx());
	}

	@Test
	void shouldDeserializeIntegerLivery() throws Exception {
		String json = "{\"gr\":0,\"as\":250,\"lv\":10071}";
		HttpMapUserStateResponse state = objectMapper.readValue(json, HttpMapUserStateResponse.class);

		assertNotNull(state);
		assertEquals(0, state.gr());
		assertEquals(250, state.as());
		assertNotNull(state.lv());
		assertNull(state.lv().ac_path());
		assertEquals(10071, state.lv().idx());
	}

	@Test
	void shouldDeserializeNullLivery() throws Exception {
		String json = "{\"gr\":0,\"as\":0,\"lv\":null}";
		HttpMapUserStateResponse state = objectMapper.readValue(json, HttpMapUserStateResponse.class);

		assertNotNull(state);
		assertNull(state.lv());
	}

	@Test
	void shouldDeserializeFullMapResponse() throws Exception {
		String json = """
				{
				  "userCount": 3,
				  "users": [
				    {
				      "id": "123",
				      "acid": 456,
				      "ac": 1,
				      "cs": "TEST1",
				      "st": {"gr": 1, "as": 8, "lv": "3"},
				      "co": [48.8566, 2.3522, 100.0, 0.0, 0.0, 90.0],
				      "ve": [10.0, 0.0, 0.0, 0.0, 0.0, 0.0],
				      "ti": 1700000000.0
				    },
				    {
				      "id": "124",
				      "acid": 457,
				      "ac": 2,
				      "cs": "TEST2",
				      "st": {"gr": 1, "as": 0},
				      "co": [40.7128, -74.0060, 500.0, 0.0, 0.0, 180.0],
				      "ve": [0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
				      "ti": 1700000001.0
				    },
				    {
				      "id": "125",
				      "acid": 458,
				      "ac": 0,
				      "cs": "TEST3",
				      "st": {"gr": 0, "as": 250, "lv": {"ac_path": "a350", "idx": 10071}},
				      "co": [51.5074, -0.1278, 30000.0, 0.0, 0.0, 270.0],
				      "ve": [250.0, 0.0, 0.0, 0.0, 0.0, 0.0],
				      "ti": 1700000002.0
				    }
				  ]
				}
				""";

		HttpMapResponse response = objectMapper.readValue(json, HttpMapResponse.class);
		assertNotNull(response);
		assertEquals(3, response.userCount());
		assertEquals(3, response.users().size());
		assertEquals(3, response.users().get(0).st().lv().idx());
		assertNull(response.users().get(1).st().lv());
		assertEquals("a350", response.users().get(2).st().lv().ac_path());
		assertEquals(10071, response.users().get(2).st().lv().idx());
	}
}
