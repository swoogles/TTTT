---
layout: slide
title:  "Catching Exceptions and Returning Null"
categories: testable-types
---

* By catching Exception/RuntimeException, you obscure the fact that we've reached a fatal state
* If you get null as a response, is that because there was no record in the Database, or because you failed to communicate with the Database at all?

{% highlight java %}
public void getRecordFromDatabase(String id) {
    try {
        return sqlCommand("SELECT * FROM table WHERE id = :id ");
    } catch (Exception ex) {
        return null;
    }
}
{% endhighlight %}
