package at.dru.wicketblog.service;

import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EntityServiceRegistry {

    private final Map<Class<?>, EntityService<?>> entityServiceMap = new ConcurrentHashMap<>();

    public void registerService(@Nonnull EntityService<?> entityService) {
        this.entityServiceMap.put(entityService.getEntityType(), entityService);
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public <T> EntityService<T> forClass(@Nonnull Class<T> entityClass) {
        EntityService<T> entityService = (EntityService<T>) entityServiceMap.get(entityClass);

        if (entityService == null) {
            throw new ServiceException("No entity service registered for class: " + entityClass.getCanonicalName());
        }

        return entityService;
    }

}
