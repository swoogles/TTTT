---
layout: paginate_slide
title:  "Slightly-dryer-content"
categories: multi-slide
paginate: true
paginate_content:
  separator: h2
  title: ":title :num/:max: :section"
  permalink: /page:numof:max.html
---

## Slides
First slide

{% assign page_content = "hi, there, neighbor" | split: ", " %}

{% for item in page_content %}
- {{ item }}
{% endfor %}

## Slides2
Second slide
{% assign page_content2 = "hello, there, chum" | split: ", " %}
{% for item in page_content2 %}
- {{ item }}
{% endfor %}



## Slides3
{% assign page_content = "hi, there, neighbor" | split: ", " %}
{% assign page_content2 = "hello, there, chum" | split: ", " %}
{% assign page_content3 = page_content1 | concat: page_content2  %}
{% for item in page_content3 %}
- {{ item }}
{% endfor %}
