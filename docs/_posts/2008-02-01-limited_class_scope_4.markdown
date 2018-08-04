---
layout: slide
title:  "Simple Endpoint"
categories: testable-types
---

{% highlight java %}
    WebPage get(UUID userId) {
        return (userLogic.isNew(userId))
            ? NEW_USER_PAGE;
            : ESTABLISHED_USER_PAGE;
    }
{% endhighlight %}
