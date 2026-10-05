/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client.httpClient.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class HttpMapUserStateLiveryDeserializer extends JsonDeserializer<HttpMapUserStateLiveryResponse> {

	@Override
	public HttpMapUserStateLiveryResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		JsonToken token = p.currentToken();
		if (token == JsonToken.VALUE_STRING) {
			String text = p.getText();
			if (text == null || text.isBlank()) {
				return null;
			}
			try {
				int idx = Integer.parseInt(text.trim());
				return new HttpMapUserStateLiveryResponse(null, idx);
			} catch (NumberFormatException ignored) {
				return new HttpMapUserStateLiveryResponse(text.trim(), null);
			}
		} else if (token == JsonToken.VALUE_NUMBER_INT) {
			return new HttpMapUserStateLiveryResponse(null, p.getIntValue());
		} else if (token == JsonToken.START_OBJECT) {
			JsonNode node = p.getCodec().readTree(p);
			String acPath = null;
			if (node.hasNonNull("ac_path")) {
				acPath = node.get("ac_path").asText();
			} else if (node.hasNonNull("acPath")) {
				acPath = node.get("acPath").asText();
			}

			Integer idx = null;
			if (node.hasNonNull("idx")) {
				JsonNode idxNode = node.get("idx");
				if (idxNode.isInt() || idxNode.isNumber()) {
					idx = idxNode.asInt();
				} else if (idxNode.isTextual()) {
					try {
						idx = Integer.parseInt(idxNode.asText().trim());
					} catch (NumberFormatException ignored) {
					}
				}
			}
			return new HttpMapUserStateLiveryResponse(acPath, idx);
		} else if (token == JsonToken.VALUE_NULL) {
			return null;
		}
		return null;
	}
}
