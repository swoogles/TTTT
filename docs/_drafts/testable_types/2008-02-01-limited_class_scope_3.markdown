---
layout: slide
title:  "Bite-sized Components"
categories: testable-types
---
{% highlight java %}
interface PostMapper {
    List<Post> userPosts(UUID userId);
}
interface UserMapper {
    User get(UUID userId);
}
class UserLogic {
    boolean isNew(UUID userId) {
        User user = userMapper.get(userId);
        List<Post> userPosts = postMapper.userPosts(userId);
        
        return user.getSubscribeDate().after(oneWeekAgo) 
                && userPosts.length < 10
    }
}
{% endhighlight %}
