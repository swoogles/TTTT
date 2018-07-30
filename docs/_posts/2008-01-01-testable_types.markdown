---
layout: slide
title:  "Testable types"
categories: testable-types section-title
---


* The handcuffs of calling an impure static-final method.
    * Instant.now(), DateTime.now(), etc. are *extremely* common offenders.
* Catching Exception and returning null.
* Use the constructor, that's what it's for!
* Limited class scope.

* *Most* best practices will payoff in easier testing in some way.
