/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = HttpMapUserStateLiveryDeserializer.class)
public record HttpMapUserStateLiveryResponse(
		@JsonProperty("ac_path") String ac_path,
		@JsonProperty("idx") Integer idx) {

	public String acPath() {
		return ac_path;
	}
}
