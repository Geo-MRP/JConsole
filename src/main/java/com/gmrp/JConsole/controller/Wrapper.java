/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.controller;

import com.gmrp.JConsole.client.Client;
import com.gmrp.JConsole.service.Service;

import java.util.Objects;

public class Wrapper {
	private final Client client;
	private final Service service;

	public Wrapper(Client client, Service service) {
		this.client = Objects.requireNonNull(client);
		this.service = Objects.requireNonNull(service);
	}
}