/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.service;

public interface ServiceConfig {
	String getType();

	ServiceConfig fromEnv();

	Service createService();
}