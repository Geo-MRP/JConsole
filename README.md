<!--
Copyright (C) 2026 NickFury001
SPDX-License-Identifier: AGPL-3.0-or-later
-->
# Java Console (API Wrapper)

<p align="center">
  <img src="./src/main/resources/assets/images/logo.png" width=30% height=30% />
</p>

<p align="center">
  An open-source Java port of Fury's original closed-source python-based Console API Wrapper.
</p>

---
<p align="center">
  <a href="#support">
    <img src="https://img.shields.io/badge/Support-PayPal%20%26%20Ko%2D%2DFi-EA4AAA?style=for-the-badge&logo=githubsponsors&logoColor=white" alt="Support GMRP">
  </a>
</p>

---

## Info

The goal of this open-source initiative is to ensure the long-term development, maintenance, and survivability of technology initially made by [ghost_of_denver](https://discord.com/users/1510279232201298022) for the [GeoFS Military Roleplay Community](https://discord.com/invite/updVrRXm4P) on Discord.

This repository aims to cover the API Wrapper behavior of Console. That is, it's going to be an API Wrapper with multiple different features and services, among other things, to serve the GMRP Community.

## Using JConsole
### Initial Setup

Before we get started, you'll need to set up your local environment and get the code from the repository.
If you don't have [Java 26 or later](https://www.oracle.com/java/technologies/downloads/) and [Maven](https://maven.apache.org/install.html) installed, install those first.

Clone the repository:
```bash
git clone https://github.com/Geo-MRP/JConsole.git
```
Navigate to the directory and install the dependencies:
```bash
cd JConsole
mvn clean install
```

### Setting up the local environment - *WIP*
JConsole is incredibly extensive, by design. That means that you can connect it to your application in a LOT of different ways. JConsole is still mostly a work in progress, but in the future, the way it will work will be that you will have two main ways of using JConsole:
- As a dedicated microservice. By setting a specific environment variable, you'll be able to run JConsole as a standalone application. The benefits of this approach are that you don't have to implement any additional middleware to connect your application to JConsole. You can connect JConsole to output to an Oracle SQL server, a local SQLite connection, or make it self-host an HTTP REST API.
- As a library. By adding JConsole as a dependency to your project, you'll be able to use its features within your own Java application. In the future, JConsole will implement C/C++ bindings, allowing you to use it in C/C++ applications as well as potentially supporting more languages in the future.

## Contributing
Before opening a pull request, please read the contribution guidelines:

**[CONTRIBUTING.md](docs/CONTRIBUTING.md)**

The guide covers:
- Coding style and formatting
- AI-assisted contributions
- Pull request expectations
- Testing guidelines

For an overview of the codebase structure, see [ARCHITECTURE.md](docs/ARCHITECTURE.md)

## Security

We take security of this project seriously. If you discover a security vulnerability, please do not report it in the public issues tracker. Instead, refer to our [Security Policy](docs/SECURITY.md) for instructions on how to properly and responsibly report vulnerabilities.

## Support

Support Geo-MRP via PayPal or Ko-Fi. Every contribution helps fund ongoing development, long-term maintenance. PayPal is the preferred way to support the project.

[![PayPal](https://img.shields.io/badge/PayPal-00457C?style=for-the-badge&logo=paypal&logoColor=white)](https://paypal.me/GMRPTech)
[![Ko-fi](https://img.shields.io/badge/Ko--fi-F16061?style=for-the-badge&logo=ko-fi&logoColor=white)](https://ko-fi.com/GMRP)

## License

JConsole - API Wrapper
Copyright (C) 2026 NickFury001

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.