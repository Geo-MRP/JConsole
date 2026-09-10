/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client;

public interface ClientConfig {
	String getType();

	ClientConfig fromEnv();

	Client createClient();
}