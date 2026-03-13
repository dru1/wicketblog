package at.dru.wicketblog.service;

import org.jspecify.annotations.Nullable;

public interface EntityService<T> {

    Class<T> getEntityType();

    void saveEntity(T entity);

    @Nullable
    T findByEntityId(Long entityId);

    Iterable<T> findAll();

}
