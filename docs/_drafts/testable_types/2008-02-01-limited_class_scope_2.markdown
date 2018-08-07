---
layout: slide
title:  "Overly Ambitious Endpoint"
categories: testable-types
---

{% highlight java %}
    WebPage get(UUID userId) {
        List<Post> userPosts = 
          sqlQuery("SELECT * FROM posts...")
            
        User user = userMapper.get(session, userId);
        
        boolean userIsNew = 
            user.getSubscribeDate().after(oneWeekAgo) 
                && userPosts.length < 10
            
        return (userIsNew)
            ? NEW_USER_PAGE;
            : ESTABLISHED_USER_PAGE;
    }
{% endhighlight %}
