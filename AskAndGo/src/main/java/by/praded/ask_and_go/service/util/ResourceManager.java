package by.praded.ask_and_go.service.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Kiryl Praded
 * 16.12.2020
 * <p>
 * Resource manager to get access to resources.
 */
public class ResourceManager {
    /**
     * Name of configuration file.
     */
    private static final String APPLICATION_CONFIGURATION_FILE = "application";

    /**
     * Static instance to realize singleton.
     */
    private static ResourceManager instance;

    /**
     * Bundle to access configuration {@link ResourceManager#APPLICATION_CONFIGURATION_FILE}.
     */
    private final ResourceBundle configuration;

    /**
     * Private class constructor.
     */
    private ResourceManager() {
        configuration = ResourceBundle.getBundle(APPLICATION_CONFIGURATION_FILE, Locale.getDefault());
    }

    /**
     * Static method to get class instance {@link ResourceManager#instance}.
     *
     * @return instance of resource manager class.
     */
    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    /**
     * Method to find configuration value from {@link ResourceManager#configuration}
     *
     * @param name - name of configuration parameter to find.
     * @return value of config parameter.
     */
    public String findConfigurationValue(String name) {
        return configuration.getString(name);
    }
}
