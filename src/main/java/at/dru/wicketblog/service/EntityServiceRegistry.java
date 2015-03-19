package at.dru.wicketblog.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EntityServiceRegistry {

    private Map<Class, EntityService> entityServiceMap = new HashMap<>();

    public void registerService(Class forClass, EntityService entityService) {
        this.entityServiceMap.put(forClass, entityService);
    }

    public <T> EntityService<T> forClass(Class<T> forClass) {
        EntityService<T> entityService = entityServiceMap.get(forClass);

        if (entityService == null) {
            throw new ServiceException("No entityService registered for class: " + forClass);
        }

        return entityService;
    }


}
