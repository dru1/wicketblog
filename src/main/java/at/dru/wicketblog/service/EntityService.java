package at.dru.wicketblog.service;

import javax.annotation.Nonnull;

public interface EntityService<T> {

    @Nonnull
    Class<T> getEntityType();

    void saveEntity(@Nonnull T entity);

    @Nonnull
    Iterable<T> findAll();

}
