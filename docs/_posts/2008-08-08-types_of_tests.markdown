---
layout: slide
title:  "Types of Tests"
categories: presentations
paginate: true
paginate_content:
  separator: h1
  title: ":section"
  permalink: /page:numof:max.html
---
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
{:.center}
## Types of Tests and Testable Types

{:.center}
[github.com/swoogles/TTTT](github.com/swoogles/TTTT)

# Why talk about this stuff?
* Unrelated Test Failures 
* Long, complicated test cases
* Irreproducible failures

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

* Zero setup.
* Do not depend on any other implementations
* No periodic failures
* Screaming fast

# Unit Tests
{:.failure_image}
![Unit Tests]({{ "/assets/images/01_unit_tests.png" | absolute_url }}){:class="img-responsive"}

# Boundary Tests

* Requires some setup
* Test one "neighboring" service
* Can introduce periodic failures
* Slower

# Boundary Tests
{:.failure_image}
![Boundary Tests]({{ "/assets/images/02_boundary_tests.png" | absolute_url }}){:class="img-responsive"}

# Integration Tests - Internal

* More complicated to setup
* Depends on multiple implementations
* Multiple sources of periodic failures
* Multiple slow dependencies

# Integration Tests - Internal
{:.failure_image}
![Integration Tests]({{ "/assets/images/03_integration_tests_internal.png" | absolute_url }}){:class="img-responsive"}

# End-to-End Tests
* Maximally complicated to setup
* Requires *all* implementations in application
* Almost as slow as it gets
* Will confirm user expectations

# End-to-End Tests
{:.failure_image}
![End-to-End Tests]({{ "/assets/images/04_end_to_end_tests.png" | absolute_url }}){:class="img-responsive"}

# Selenium Tests
* More local environment dependencies
* Slow enough to watch them happen in real time!
* Still Necessary

# Selenium Tests
{:.failure_image}
![Selenium Tests]({{ "/assets/images/05_selenium_tests.png" | absolute_url }}){:class="img-responsive"}


# Integration Tests - External

* You have no control over your dependencies
* Human-contact with another organization
* Is the other organization obligated to help?
* Clean explanation of production failures

# Integration Tests - External
{:.failure_image}
![Integration Tests]({{ "/assets/images/06_integration_tests_external.png" | absolute_url }}){:class="img-responsive"}














# Code Coverage
{:.center}
## How should we achieve full coverage?


# Code Coverage
{:.full_image}
![Code Coverage]({{ "/assets/images/coverage/01.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.full_image}
![Code Coverage]({{ "/assets/images/coverage/02.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.full_image}
![Code Coverage]({{ "/assets/images/coverage/03.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.full_image}
![Code Coverage]({{ "/assets/images/coverage/04.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.full_image}
![Code Coverage]({{ "/assets/images/coverage/05.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
{:.full_image}
![Code Coverage]({{ "/assets/images/coverage/06.png" | absolute_url }}){:class="img-responsive"}

# Code Coverage
|   Test Type   |   Coverage            |
|  -------      |   --------            |
| Unit          |    Additive           |
| Integration   |  Combinatorial        |
| End-to-End    | max(Combinatorial)    |
| Boundary      |   Additive            |

# Live Demo
* A generic `Application` with fallible components
* How service disruptions affect multiple suites
* Experience some intermittent failures.

{% comment %}
NOT demoing combination explosion
{% endcomment %}

# Demo Concepts
{% highlight java %}
class ServiceStatus {
    void ensureServiceIsRunning(String name) {
        // Check property file and throw a RuntimeException 
        // if turned off.
    }
}
{% endhighlight %}

# Demo Concepts
{% highlight java %}
abstract class UnreliableService {
    private List<UnreliableService> dependencies;
    
    Duration fallibleAction() {
        ServiceStatus.ensureServiceIsRunning(serviceName);
        this.dependencies.forEach(
            dependency->dependency.fallibleAction
        );
        ...
    }
}
{% endhighlight %}

# Demo Concepts

## UnreliableService Implementations

* Mapper
* Logic
* RestResource
* ThirdPartyResource
* Application

# Demo Concepts
* TestSuiteCalculator
    * Organization - How many people?
    * CodeBase - How many classes?
    * TestingPeriod - How many executions?
{% comment %}
World - Mention this in context later?
{% endcomment %}

# Live Demo
{:.center}
## *Get thee to Intellij*

{% comment %}
Go to live_demo.properties
Run integration tests
Toggle a database service off
Re-Run integration tests
Run unit+boundary tests

Bump up organization and codebase

{% endcomment %}

# Time-Sensitive Tests

Concepts that can be painful to model:

"Tomorrow"

"Last year"

"In the next 6 months"

# Time-Sensitive Tests
We want tests that:
* Work today
* Work tomorrow
* Work after humanity has become multi-planetary


# Time-Sensitive Tests
{% highlight java %}
    void test() {
        List<Event> lastTen =
            eventLogic.inLast10Minutes(Instant.now());
        // Other commands...
        List<Event> lastOne = 
            eventLogic.inLastMinute(Instant.now());
            
        assertTrue( lastTen.containsAll(lastOne) );
    }
{% endhighlight %}

# Entire Test runs at the current Instant
{% highlight java %}
    void test() {
        Instant now = Instant.now();
        
        List<Event> lastTen = eventLogic.inLast10Minutes(now);
        // ... Other commands/assertions ...
        List<Event> lastOne = eventLogic.inLastMinute(now);
        
        assertTrue( lastTen.containsAll(lastOne) );
    }
{% endhighlight %}

# Entire Test runs at a fixed Instant
{% highlight java %}
    Clock clock = Clock.fixed(
                    Instant.parse("2018-08-08T00:00:00Z"),
                    ZoneId.systemDefault());
    void test() {
        Instant now = clock.instant();
        
        List<Event> lastTen = eventLogic.inLast10Minutes(now);
        // Other commands ...
        List<Event> lastOne = eventLogic.inLastMinute(now);
        
        assertTrue( lastTen.containsAll(lastOne) );
    }
{% endhighlight %}

# Completely Time-Insensitive Test
{% highlight java %}
    Clock clock = Clock.fixed(
                    Instant.parse("2018-08-08T00:00:00Z"),
                    ZoneId.of("America/Denver"));
    void test() {
        Instant now = clock.instant();
        
        List<Event> lastTen = eventLogic.inLast10Minutes(now);
        // Other commands ... 
        List<Event> lastOne = eventLogic.inLastMinute(now);
        
        assertTrue( lastTen.containsAll(lastOne) );
    }
{% endhighlight %}



# Solo Project Stats

|codebase | instances | runtime |
|--------|---------|--------|
{% for dataLine in site.data.solo_project -%}
| {{ dataLine.codebase}} | {{ dataLine.dependencies}} | {{ dataLine.runtime}} |
{% endfor %}

# Mid-sized Organization Stats

|codebase | instances | runtime |
|--------|---------|--------|
{% for dataLine in site.data.midsized -%}
| {{ dataLine.codebase}} | {{ dataLine.dependencies}} | {{ dataLine.runtime}} |
{% endfor %}

# Questions
{:.center}
Have any?

---
