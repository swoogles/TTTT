---
layout: slide
title:  "Limit Class Scope"
categories: testable-types
---

* Your API endpoint should not be defining Date formats or constructing SQL
* A class that does too much creates its own mini-hierarchy of Integration Test failures

{% highlight java %}
public class AmbitiousEndpoint {
    public Response get(String userId) {
        User user = userMapper.get(userId);
        
        List<Post> userPosts = 
            sqlQuery("SELECT * FROM user_posts WHERE user_id = :userId")
            
        boolean userIsNew = 
            user.getSubscribeDate().after(oneWeekAgo) && userPosts.length < 10
            
        if (userIsNew) {
            return NEW_USER_PAGE;
        } else {
            return ESTABLISHED_USER_PAGE;
        }
    }
}
{% endhighlight %}
