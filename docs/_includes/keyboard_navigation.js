function setup_keypress() {
    document.onkeydown = function(e) {
        switch (e.keyCode) {
            {% if paginator.has_previous %}
            case 37: // left
                document.location.href = "{{ paginator.previous_path }}";
                break;
            {% endif %}
            case 38: // up
                document.location.href = "{{ paginator.single_page }}#{{ paginator.section_id }}";
                break;
            {% if paginator.has_next %}
            case 39: // right
                document.location.href = "{{ paginator.next_path }}";
                break;
            {% endif %}
        }
    }
}