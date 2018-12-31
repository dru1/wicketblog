package at.dru.wicketblog.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface EntityService<T> {

    @Nonnull
    Class<T> getEntityType();

    void saveEntity(@Nonnull T entity);

    @Nullable
    T findByEntityId(@Nonnull Long entityId);

    @Nonnull
    Iterable<T> findAll();

}
