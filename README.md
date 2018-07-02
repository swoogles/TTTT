[![Build Status](https://travis-ci.org/swoogles/TTTT.svg?branch=master)](https://travis-ci.org/swoogles/TTTT)
[![codecov](https://codecov.io/gh/swoogles/TTTT/branch/master/graph/badge.svg)](https://codecov.io/gh/swoogles/TTTT)

# Testable Types and Types of Testing

This project is completely Educational. I think it will be purely to facilitate some presentations.
Who knows? Maybe it will spin off some decent code of its own.

## Existing Features:
Describe Test Environment to determine number of tests to run. This is based on:
	
	* Developers lists
	* Hours in the work day
	* Runs per developer per hour
	* Number of specific test classes
			* Applications
			* Mappers
			* Controllers
			* Third Party Resources
			* Kafka Producers

Fail groups of integration tests based on what services are available:
	
 
  * Database
  * Network
  * Intranet
  * Kafka Cluster
  * Application
  * Web Driver
  * Browser
  * Auth Service
  * Third Party Services
  * More to come...

## Goals:

* Easily calculate/display total runtime for a given configuration
* Have several scenarios defined via property files, to display impact of testing decisions
		* Total runtime
		* Likelihood of intermitant failures

