---
layout: slide
title:  "Exceptions Returning Null"
categories: testable-types
---

{% highlight java %}
public void getRecordFromDatabase(String id) {
    try {
        return sqlCommand("SELECT * FROM table WHERE id = :id ");
    } catch (Exception ex) {
        return null;
    }
}
{% endhighlight %}

* Now your test for `Logic.getFor(String id)` fails.
