---
layout: slide
title:  "ManyStages Live"
categories: presentations
paginate: true
paginate_content:
  separator: h2
  title: ":section"
  permalink: /page:numof:max.html
---

## Priming
* How many times has a test failed because of something *completely* unrelated to your code?
* Do you ever think "Maybe if I run the exact same tests again, they will pass." ?
* Are your test cases regularly 50+ lines long?
* Will the logic tests you've written fail if the Database schema changes?


## Non-Objectives
* Going into the details of any particular testing framework
* Convince you to do Test Driven Development
* Completely stop you from ever writing another Selenium test


## Objectives
* Increase concern for seemingly innocent decisions in class design
    * “It’s just one little call to a final static method...”
* Fight back against exploding CI testing time
* Keep tests focused on the actual behavior of your class

## Types of Tests
### Unit

### Integration (Interior)

### Selenium

### Integration (Exterior)

### Boundary


## Unit Tests

* Extremely Simple to setup
* Do not depend on any external implementations
* No periodic failures
* Screaming fast

## Integration Tests

* More complicated to setup
* Depends on 1+ external implementations
* Can introduce periodic failures
* Substantially slower

## Selenium Tests

* Most complicated to setup
* Requires *all* implementations for the entire application
* Slow enough you can watch them happen in real time!
* Still Necessary

## Boundary Tests

* Focused testing of one external service
* Necessary to see what happens when the rubber meets the road
* Data layer, REST API Client, etc

## Code Coverage

| Unit | Integration | Selenium | Boundary |
|-------|--------|---------|--------|
| Additive | Combinatorial | max(Combinatorial) | Additive


## Integration Test Failures

Hop to other slides for now.



---
