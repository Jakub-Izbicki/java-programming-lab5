package jakub.izbicki.java.lab5.commons;

import java.io.IOException;

public final class Properties {

    private static final String PROPERTIES_FILE = "/application.properties";

    private static final String COULD_NOT_LOAD_PROPERTIES = "Unable to load properties file.";

    private java.util.Properties properties;

    public Properties() {
        properties = new java.util.Properties();
        try {
            properties.load(getClass().getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException e) {
            throw new IllegalStateException(COULD_NOT_LOAD_PROPERTIES, e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
