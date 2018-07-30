---
layout: slide
title:  "Catching Exceptions and Returning Null"
categories: testable-types
---

* By catching Exception/RuntimeException, you obscure that we've reached a fatal state
* Is the result null, because there was no record in the Database, or because you failed to communicate with the Database at all?

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
    * The Logic test is the scape-goat when the problem occurred in the Mapper.