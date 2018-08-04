package com.billding.tttt;

import javax.naming.ConfigurationException;
import java.time.Duration;
import java.util.IllegalFormatException;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

// TODO Oo! This class can demonstrate failures when the filesystem changes!
// resource_directory=/home/developer
// resource_directory=/home/bamboo

/**
 * This class is the simplest form of getting .properties that I can manage at any given time.
 */
public class PropertyRetriever {
    private final ResourceBundle bundle;

    // TODO Take filename as parameter.
    public PropertyRetriever(String resourceName) {
        this.bundle = ResourceBundle.getBundle(resourceName);
    }

    public boolean getBinaryBoolean(String name) {
        try {
            String propertyValue = this.bundle.getString(name);
            if(propertyValue.equals("1")) {
                return true;
            } else if (propertyValue.equals("0")) {
                return false;
            } else {
                throw new IllegalArgumentException(
                        "Unrecogized boolean value. \n"
                        + "Provided: " + propertyValue + "\n"
                        + "Required: 0 or 1" );
            }
        } catch (MissingResourceException ex) {
            // TODO Put this error handling in string method and/or unify with duration error reporting
            throw new RuntimeException("resource file: " + bundle.getBaseBundleName() + "  key: " + name, ex);
        }
    }

    public int getInt(String name) {
        try {
            return Integer.parseInt(this.bundle.getString(name));
        } catch (MissingResourceException ex) {
            // TODO Put this error handling in string method and/or unify with duration error reporting
            throw new RuntimeException("resource file: " + bundle.getBaseBundleName() + "  key: " + name, ex);
        }
    }

    public String getString(String name) {
        return Objects.requireNonNull(bundle.getString(name));
    }

    public Duration getShortDuration(String name) {
        final String rawValue = getString(name);
        if (rawValue.endsWith(" ms") ) {
            int numMilliSeconds = Integer.parseInt(
            rawValue.substring(0, rawValue.length() - 3)
            );
            return Duration.ofMillis(numMilliSeconds);
        } else if( rawValue.endsWith(" s") ){
            int numSeconds = Integer.parseInt(
                rawValue.substring(0, rawValue.length() - 2)
            );
            return Duration.ofSeconds(numSeconds);
        } else {
            throw new RuntimeException("Failed to parse short duration property " + name + " in property file: " + this.bundle.getBaseBundleName() );
        }

    }
}
