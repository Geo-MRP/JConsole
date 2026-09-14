/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.model;

public record Twist(
		LinearVelocity linearVelocity,
		AngularVelocity angularVelocity) {
}