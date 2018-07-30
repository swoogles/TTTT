---
layout: slide
title:  ""
categories: 
---

The story of the agonizing test:
* I need to test $someClass.myNewMethod
* To do anything with `$someClass`, I need to:
    * Create `$theseThings`
    * Configure `$thoseOtherThings`
* Damnit, now I need to call 
{% highlight java %}
$totallyForeignClass.init(int numberOfBollyWips, boolean doesAntiFrumpADoop)
$totallyForeignClass.init(int numberOfBollyWips, boolean shouldNotProceedAgainstFiltering)
{% endhighlight %}
>> ?!?!

* *Receive email that you are responsible for a broken build*

(Because someone slipped a schema change in during your development)

