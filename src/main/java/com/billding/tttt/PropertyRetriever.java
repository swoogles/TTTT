package com.billding.tttt;

import java.util.ResourceBundle;

// TODO Oo! This class can demonstrate failures when the filesystem changes!
// resource_directory=/home/developer
// resource_directory=/home/bamboo
public class PropertyRetriever {
    private final ResourceBundle bundle;

    // TODO Take filename as parameter.
    public PropertyRetriever(String resourceName) {
        this.bundle = ResourceBundle.getBundle(resourceName);
    }

    public boolean getBoolean(String name) {
        return this.bundle.containsKey(name) && this.bundle.getString(name).equals("true");
    }

    public int getInt(String name) {
        return Integer.parseInt(this.bundle.getString(name));
    }

    public String getString(String name) {
        return this.bundle.getString(name);
    }
}
