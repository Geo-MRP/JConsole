/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient;

import com.gmrp.JConsole.client.httpClient.dto.HttpMapUserResponse;
import com.gmrp.JConsole.client.httpClient.dto.HttpMapUserStateLiveryResponse;
import com.gmrp.JConsole.client.httpClient.dto.HttpMapUserStateResponse;
import com.gmrp.JConsole.client.httpClient.mapper.HttpUserMapper;
import com.gmrp.JConsole.model.User;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpUserMapperTest {

	@Test
	void shouldReturnNullWhenDtoIsNull() {
		assertNull(HttpUserMapper.toDomain(null));
	}

	@Test
	void shouldMapUserWithCustomAircraftLivery() {
		HttpMapUserStateLiveryResponse livery = new HttpMapUserStateLiveryResponse("a350", 10071);
		HttpMapUserStateResponse state = new HttpMapUserStateResponse(0, 240, livery);
		HttpMapUserResponse dto = new HttpMapUserResponse(
				"100",
				200,
				0,
				"AFR123",
				state,
				List.of(48.8566, 2.3522, 10000.0, 1.0, 2.0, 90.0),
				List.of(240.0, 0.0, 5.0, 0.1, 0.0, 0.2),
				1700000000.0);

		User user = HttpUserMapper.toDomain(dto);

		assertNotNull(user);
		assertEquals(100, user.id());
		assertEquals(200, user.accountId());
		assertEquals("AFR123", user.callsign());
		assertFalse(user.isGrounded());
		assertEquals(240, user.airspeed());
		assertEquals(Instant.ofEpochMilli(1700000000000L), user.timestamp());

		assertNotNull(user.aircraft());
		assertEquals(0, user.aircraft().id());
		assertEquals("a350", user.aircraft().name());

		assertNotNull(user.geoPose());
		assertEquals(48.8566, user.geoPose().position().coordinates().latitude());
		assertEquals(2.3522, user.geoPose().position().coordinates().longitude());
		assertEquals(10000.0, user.geoPose().position().altitude());
		assertEquals(1.0, user.geoPose().orientation().roll());
		assertEquals(2.0, user.geoPose().orientation().pitch());
		assertEquals(90.0, user.geoPose().orientation().yaw());

		assertNotNull(user.twist());
		assertEquals(240.0, user.twist().linearVelocity().x());
		assertEquals(0.0, user.twist().linearVelocity().y());
		assertEquals(5.0, user.twist().linearVelocity().z());
		assertEquals(0.1, user.twist().angularVelocity().rollRate());
		assertEquals(0.0, user.twist().angularVelocity().pitchRate());
		assertEquals(0.2, user.twist().angularVelocity().yawRate());
	}

	@Test
	void shouldMapUserWithNumericLivery() {
		HttpMapUserStateLiveryResponse livery = new HttpMapUserStateLiveryResponse(null, 3);
		HttpMapUserStateResponse state = new HttpMapUserStateResponse(1, 10, livery);
		HttpMapUserResponse dto = new HttpMapUserResponse(
				"101",
				201,
				1,
				"CES1",
				state,
				List.of(48.8566, 2.3522, 100.0, 0.0, 0.0, 0.0),
				List.of(10.0, 0.0, 0.0, 0.0, 0.0, 0.0),
				1700000000.0);

		User user = HttpUserMapper.toDomain(dto);

		assertNotNull(user);
		assertTrue(user.isGrounded());
		assertEquals(10, user.airspeed());
		assertEquals(1, user.aircraft().id());
		assertEquals("", user.aircraft().name());
	}

	@Test
	void shouldMapUserWithoutLivery() {
		HttpMapUserStateResponse state = new HttpMapUserStateResponse(1, 0, null);
		HttpMapUserResponse dto = new HttpMapUserResponse(
				"102",
				202,
				2,
				"CES2",
				state,
				List.of(48.8566, 2.3522, 100.0, 0.0, 0.0, 0.0),
				List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
				1700000000.0);

		User user = HttpUserMapper.toDomain(dto);

		assertNotNull(user);
		assertTrue(user.isGrounded());
		assertEquals(0, user.airspeed());
		assertEquals(2, user.aircraft().id());
		assertEquals("", user.aircraft().name());
	}

	@Test
	void shouldHandleNullStateAndLists() {
		HttpMapUserResponse dto = new HttpMapUserResponse(
				"abc",
				203,
				3,
				"CES3",
				null,
				null,
				null,
				1700000000.0);

		User user = HttpUserMapper.toDomain(dto);

		assertNotNull(user);
		assertEquals("abc".hashCode(), user.id());
		assertFalse(user.isGrounded());
		assertEquals(0, user.airspeed());
		assertNull(user.geoPose());
		assertNull(user.twist());
		assertEquals(3, user.aircraft().id());
		assertEquals("", user.aircraft().name());
	}
}
