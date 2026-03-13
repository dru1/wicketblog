package at.dru.wicketblog.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EntityServiceRegistry {

    private final Map<Class<?>, EntityService<?>> entityServiceMap = new ConcurrentHashMap<>();

    public void registerService(EntityService<?> entityService) {
        this.entityServiceMap.put(entityService.getEntityType(), entityService);
    }

    @SuppressWarnings("unchecked")
    public <T> EntityService<T> forClass(Class<T> entityClass) {
        EntityService<T> entityService = (EntityService<T>) entityServiceMap.get(entityClass);

        if (entityService == null) {
            throw new ServiceException("No entity service registered for class: " + entityClass.getCanonicalName());
        }

        return entityService;
    }

}
