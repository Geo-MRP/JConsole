/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.controller;

import com.gmrp.JConsole.client.Client;
import com.gmrp.JConsole.client.ClientConfig;
import com.gmrp.JConsole.service.Service;
import com.gmrp.JConsole.service.ServiceConfig;

import java.util.ServiceLoader;
import java.util.function.Supplier;

public class WrapperConfig {
	private static final String DEFAULT_CLIENT = "HTTP";
	private static final String DEFAULT_SERVICE = "";

	private ClientConfig clientConfig;
	private ServiceConfig serviceConfig;

	public WrapperConfig fromEnv() {
		String clientType = System.getenv().getOrDefault("CLIENT", DEFAULT_CLIENT);
		String serviceType = System.getenv().getOrDefault("SERVICE", DEFAULT_SERVICE);

		createClientConfig(clientType);
		createServiceConfig(serviceType);

		return this;
	}

	private void createClientConfig(String clientType) {
		if (clientType == null)
			throw new IllegalArgumentException("Missing environment variable: CLIENT");

		ServiceLoader<ClientConfig> clientConfigLoader = ServiceLoader.load(ClientConfig.class);
		Supplier<ClientConfig> clientConfigSupplier = clientConfigLoader.stream()
				.filter(provider -> provider.get().getType().equals(clientType))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("Unsupported client: " + clientType));

		clientConfig = clientConfigSupplier.get().fromEnv();
	}

	private void createServiceConfig(String serviceType) {
		if (serviceType == null)
			throw new IllegalArgumentException("Missing environment variable: SERVICE");

		ServiceLoader<ServiceConfig> serviceConfigLoader = ServiceLoader.load(ServiceConfig.class);
		Supplier<ServiceConfig> serviceConfigSupplier = serviceConfigLoader.stream()
				.filter(provider -> provider.get().getType().equals(serviceType))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("Unsupported service: " + serviceType));

		serviceConfig = serviceConfigSupplier.get().fromEnv();
	}

	public Client createClient() {
		return clientConfig.createClient();
	}

	public Service createService() {
		return serviceConfig.createService();
	}
}