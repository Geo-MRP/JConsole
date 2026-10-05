/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient.dto;

import java.util.List;

public record HttpMapResponse(
		int userCount,
		List<HttpMapUserResponse> users) {
}