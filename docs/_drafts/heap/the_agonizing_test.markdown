---
layout: slide
title:  "The Story of the Agonizing Test"
categories: untestable-types costs
---
* I need to test `$someClass.myNewMethod`
* To do anything with `$someClass`, I need to:
    * Create `$theseThings`
    * Configure `$thoseOtherThings`
* Damnit, now I need to call 
{% highlight java %}
$foreignClass.init(boolean shouldNotProceedAgainstFiltering)
{% endhighlight %}
>> ?!?!

