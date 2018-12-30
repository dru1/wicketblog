package at.dru.wicketblog.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

@Service
public class EntityServiceRegistry {

    private Map<Class<?>, EntityService<?>> entityServiceMap = new HashMap<>();

    public <E> void registerService(@Nonnull EntityService<E> entityService) {
        this.entityServiceMap.put(entityService.getEntityType(), entityService);
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public <T> EntityService<T> forClass(@Nonnull Class<T> entityClass) {
        EntityService<T> entityService = (EntityService<T>) entityServiceMap.get(entityClass);

        if (entityService == null) {
            throw new ServiceException("No entityService registered for class: " + entityClass);
        }

        return entityService;
    }

}
