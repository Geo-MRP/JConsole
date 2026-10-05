/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient.mapper;

import com.gmrp.JConsole.client.httpClient.dto.HttpMapUserResponse;
import com.gmrp.JConsole.model.Aircraft;
import com.gmrp.JConsole.model.AngularVelocity;
import com.gmrp.JConsole.model.Coordinates;
import com.gmrp.JConsole.model.GeoPose;
import com.gmrp.JConsole.model.LinearVelocity;
import com.gmrp.JConsole.model.Orientation;
import com.gmrp.JConsole.model.Position;
import com.gmrp.JConsole.model.Twist;
import com.gmrp.JConsole.model.User;

import java.time.Instant;
import java.util.List;

public class HttpUserMapper {

	private HttpUserMapper() {
	}

	public static User toDomain(HttpMapUserResponse dto) {
		if (dto == null) {
			return null;
		}

		GeoPose geoPose = mapGeoPose(dto.co());
		Twist twist = mapTwist(dto.ve());

		boolean isGrounded = dto.st() != null && dto.st().gr() == 1;
		int airspeed = dto.st() != null ? dto.st().as() : 0;

		String aircraftName = "";
		if (dto.st() != null && dto.st().lv() != null && dto.st().lv().ac_path() != null) {
			aircraftName = dto.st().lv().ac_path();
		}

		// Map aircraft ID to Aircraft domain record
		Aircraft aircraft = new Aircraft(dto.ac(), aircraftName);

		// Convert epoch timestamp (in seconds) to Instant
		Instant timestamp = Instant.ofEpochMilli((long) (dto.ti() * 1000));

		int userId = parseUserId(dto.id());

		return new User(
				userId,
				dto.acid(),
				dto.cs(),
				geoPose,
				isGrounded,
				twist,
				airspeed,
				aircraft,
				timestamp);
	}

	private static GeoPose mapGeoPose(List<Double> co) {
		if (co == null || co.size() < 6) {
			return null;
		}
		// co: [lat, lon, alt, roll, pitch, yaw]
		Coordinates coordinates = new Coordinates(co.get(0), co.get(1));
		Position position = new Position(coordinates, co.get(2));
		Orientation orientation = new Orientation(co.get(3), co.get(4), co.get(5));
		return new GeoPose(position, orientation);
	}

	private static Twist mapTwist(List<Double> ve) {
		if (ve == null || ve.size() < 6) {
			return null;
		}
		// ve: [linearX, linearY, linearZ, angularX, angularY, angularZ]
		LinearVelocity linearVelocity = new LinearVelocity(ve.get(0), ve.get(1), ve.get(2));
		AngularVelocity angularVelocity = new AngularVelocity(ve.get(3), ve.get(4), ve.get(5));
		return new Twist(linearVelocity, angularVelocity);
	}

	private static int parseUserId(String rawId) {
		if (rawId == null) {
			return 0;
		}
		try {
			return Integer.parseInt(rawId);
		} catch (NumberFormatException e) {
			return rawId.hashCode();
		}
	}
}