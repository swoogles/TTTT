---
layout: post
title:  "Types of Tests"
categories: types-of-tests
---

## Unit
* Extremely Simple to setup
* Do not depend on any external implementations
* No periodic failures

## Integration
* More complicated to setup
* Depends on 1+ external implementations
* Can introduce periodic failures

## Selenium
* Most complicated to setup
* Depends on many things:
    * All implementations for the entire application
    * running server
    * WebDriver
    * Browser

## Boundary
* Depends on exactly 1 live external implementations
* Necessary to see what happens when the rubber meets the road
* For example:
    * Data layer
    * REST API Client

{% include slide_footer.md %}
