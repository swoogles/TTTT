---
layout: slide
title:  "ManyStages Live"
categories: presentations
paginate: true
paginate_content:
  separator: h1
  title: ":section"
  permalink: /page:numof:max.html
---
{:.center}
# Title
## Types of Tests and Testable Types


# Why talk about this stuff?
* Unrelated Test Failures 
* Long, complicated test cases
* Irreproducible failures
* What can make *your* tests fail?

{% comment %} 
How many times has a test failed because of something *completely* unrelated to your code?
Do you ever think "Maybe if I run the exact same tests again, they will pass." ?
Are your test cases regularly 50+ lines long?
Will the logic tests you've written fail if the Database schema changes?
* Doing the same thing over again and getting different results?
{% endcomment %}


# Non-Objectives
* Diving into any particular testing framework
* Convince you to do Test Driven Development
* Stop you from ever writing another Selenium test


# Objectives
* Increase concern for seemingly innocent decisions in class design
* Fight back against exploding CI testing time
* Keep tests focused on the actual behavior of your class

# Types of Tests
* Unit
* Boundary
* Integration (Interior)
* End-to-End
* Selenium
* Integration (Exterior)


# Unit Tests

* Extremely Simple to setup
* Do not depend on any external implementations
* No periodic failures
* Screaming fast

# Unit Tests
{:.failure_image}
![Unit Tests]({{ "/assets/images/01_unit_tests.png" | absolute_url }}){:class="img-responsive"}

# Boundary Tests

* Focused testing of one "neighboring" service
* Where rubber meets the road
* Data layer, REST API Client, etc

# Boundary Tests
{:.failure_image}
![Boundary Tests]({{ "/assets/images/02_boundary_tests.png" | absolute_url }}){:class="img-responsive"}

# Integration Tests - Internal

* More complicated to setup
* Depends on 1+ external implementations
* Can introduce periodic failures
* Slower, varies substantially by dependencies

# Integration Tests - Internal
{:.failure_image}
![Integration Tests]({{ "/assets/images/03_integration_tests_internal.png" | absolute_url }}){:class="img-responsive"}

# End-to-End Tests
* Most complicated to setup
* Requires *all* implementations for the entire application

{% comment %}
AKA "Functional Tests"
{% endcomment %}

# End-to-end Tests
{:.failure_image}
![End-to-End Tests]({{ "/assets/images/04_end_to_end_tests.png" | absolute_url }}){:class="img-responsive"}


# Selenium Tests
* Slow enough you can watch them happen in real time!
* Still Necessary

# Selenium Tests
{:.failure_image}
![Selenium Tests]({{ "/assets/images/05_selenium_tests.png" | absolute_url }}){:class="img-responsive"}


# Integration Tests - External

* Might not need entire dependency stack
* Human-contact with another organization
* Is the other organization obligated to help you?
* Clean explanation of production failures

{% comment %}
Failure Points: Surprise Updates, Botched Deployment, Unpaid Bills
{% endcomment %}

# Integration Tests - External
{:.failure_image}
![Integration Tests]({{ "/assets/images/06_integration_tests_external.png" | absolute_url }}){:class="img-responsive"}


# Time-sensitive Tests

Concepts that can be painful to model:

"Tomorrow"

"Last year"

"In the last 6 months"

# Time-sensitive Tests
We want tests that are
* Good today
* Good tomorrow
* Good after humanity has spread to other planets


















# Code Coverage
{:.center}
## How should we approach full-coverage?


# Code Coverage
{:.failure_image}
![Code Coverage]({{ "/assets/images/coverage/01.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.failure_image}
![Code Coverage]({{ "/assets/images/coverage/02.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.failure_image}
![Code Coverage]({{ "/assets/images/coverage/03.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.failure_image}
![Code Coverage]({{ "/assets/images/coverage/04.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.failure_image}
![Code Coverage]({{ "/assets/images/coverage/05.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.failure_image}
![Code Coverage]({{ "/assets/images/coverage/06.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
|   Test Type   |   Coverage            |
|  -------      |   --------            |
| Unit          |    Additive           |
| Integration   |  Combinatorial        |
| End-to-End    | max(Combinatorial)    |
| Boundary      |   Additive            |

# Live Demo Explanation
* What I've made
* This will model how all the components of an application can fail
* NOT demoing combination explosion
* MORE WORDING HERE


# Demo Concepts
* UnreliableService
    * Mapper, RestResource, Logic, ThirdPartyResource
* TestSuiteCalculator
    * Organization
    * CodeBase
    * TestingPeriod
* ServiceStatus
* World

# Live Demo
## Get thee to Intellij

# Sandbox
{% include code_with_bullets.markdown notes = site.data.simple_list  %}

|organization | codebase | instances | runtime |
|-------|--------|---------|--------|
{% for dataLine in site.data.first -%}
| {{ dataLine.organization}} | {{ dataLine.codebase}} | {{ dataLine.instances}} | {{ dataLine.runtime}} |
{% endfor %}


---
