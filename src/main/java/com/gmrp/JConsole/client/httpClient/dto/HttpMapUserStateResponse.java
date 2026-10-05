/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient.dto;

public record HttpMapUserStateResponse(
		int gr,
		int as,
		HttpMapUserStateLiveryResponse lv) {
}