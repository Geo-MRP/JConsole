/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.gmrp.JConsole.client;

import com.gmrp.JConsole.model.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Client {
	CompletableFuture<List<User>> getUsers();
}