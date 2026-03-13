package at.dru.wicketblog.wicket.model;

import at.dru.wicketblog.model.AbstractEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.util.Assert;

public class EntityModel<E extends AbstractEntity> extends LoadableDetachableModel<E> {

    @SpringBean
    private EntityServiceRegistry entityServiceRegistry;

    private final Class<E> entityClass;
    private final Long entityId;

    public EntityModel(E entity, Class<E> entityClass) {
        Injector.get().inject(this);

        Assert.notNull(entity.getId(), "The entity must have an id.");

        this.entityClass = entityClass;
        this.entityId = entity.getId();
    }

    @Override
    protected E load() {
        return entityServiceRegistry.forClass(entityClass).findByEntityId(entityId);
    }

}
