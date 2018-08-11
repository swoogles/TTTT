// This is LIKELY where the problem with my next/prev links can be fixed.
// TODO as soon as its done here, make sure it's fixed EVERYWHERE.
function setup_keypress() {
    document.onkeydown = function(e) {
        switch (e.keyCode) {
            {% if page.previous != nil %}
            case 37: // left
                document.location.href = "{{page.previous.url | relative_url}}";
                break;
            {% endif %}
            {% if page.next != nil %}
            case 39: // right
                document.location.href = "{{page.next.url | relative_url}}";
                break;
            {% endif %}
        }
    }
}
