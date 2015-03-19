package at.dru.wicketblog.service;

import javax.annotation.Nonnull;

public interface EntityService<T> {

    Class<T> getEntityType();

    void saveEntity(@Nonnull T entity);

    Iterable<T> findAll();

}
