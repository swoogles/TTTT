function setup_keypress() {
    document.onkeydown = function(e) {
        switch (e.keyCode) {
            {% if page.previous != nil %}
            case 37: // left
                document.location.href = "{{site.url}}{{page.previous.url | relative_url}}";
                break;
            {% endif %}
            {% if page.next != nil %}
            case 39: // right
                document.location.href = "{{site.url}}{{page.next.url | relative_url}}";
                break;
            {% endif %}
        }
    }
}
