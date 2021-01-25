package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.service.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kiryl Praded
 * 28.12.2020
 * <p>
 * Service provider to keep all services and get them by class.
 */
public class ServiceProvider {

    /**
     * Instance of service provider for singleton.
     */
    private static final ServiceProvider instance = new ServiceProvider();

    /**
     * Service repository that keeps all services.
     */
    private final Map<Service, AbstractService> repository;

    /**
     * Private class constructor.
     * Initialize {@link ServiceProvider#repository} and puts all services.
     */
    private ServiceProvider() {
        repository = new HashMap<>();
        repository.put(Service.CATEGORY, new CategoryServiceImpl());
        repository.put(Service.USER, new UserServiceImpl());
        repository.put(Service.QUESTION, new QuestionServiceImpl());
        repository.put(Service.ANSWER, new AnswerServiceImpl());
        repository.put(Service.TAG, new TagServiceImpl());
    }

    /**
     * Static method to get instance of the Service provider.
     *
     * @return value of the field {@link ServiceProvider#instance}
     */
    public static ServiceProvider getInstance() {
        return instance;
    }

    /**
     * Method to take copy of the service.
     *
     * @param service    - service class to take.
     * @param <Type> - type of the service class.
     * @return copy of the service from {@link ServiceProvider#repository}.
     */
    @SuppressWarnings("unchecked")
    public <Type extends AbstractService> Type takeService(Service service) {
        return (Type) repository.get(service);
    }
}

