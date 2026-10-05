/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient.dto;

import java.util.List;

public record HttpMapUserResponse(
		String id,
		int acid,
		int ac,
		String cs,
		HttpMapUserStateResponse st,
		List<Double> co,
		List<Double> ve,
		double ti) {
}