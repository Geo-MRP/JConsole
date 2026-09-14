/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.model;

import java.time.Instant;

public record User(
		int id,
		int accountId,
		String callsign,

		GeoPose geoPose,
		boolean isGrounded,
		Twist twist,
		int airspeed,

		Aircraft aircraft,

		Instant timestamp) {
}