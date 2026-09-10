/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.controller;

import com.gmrp.JConsole.client.Client;
import com.gmrp.JConsole.service.Service;

import java.util.Objects;

public class WrapperBuilder {
	private Client client;
	private Service service;

	public WrapperBuilder withClient(Client client) {
		this.client = client;
		return this;
	}

	public WrapperBuilder withService(Service service) {
		this.service = service;
		return this;
	}

	public Wrapper build() {
		Objects.requireNonNull(client);
		Objects.requireNonNull(service);
		return new Wrapper(client, service);
	}
}