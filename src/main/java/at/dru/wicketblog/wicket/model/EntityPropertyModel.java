package at.dru.wicketblog.wicket.model;

import at.dru.wicketblog.model.AbstractEntity;
import jakarta.persistence.metamodel.Attribute;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class EntityPropertyModel<P, E extends AbstractEntity> extends PropertyModel<P> {

    private static final long serialVersionUID = 1L;

    private final IModel<E> entityModel;
    private final Class<E> entityClass;

    public EntityPropertyModel(IModel<E> entityModel, Attribute<E, P> attribute) {
        this(entityModel, attribute.getDeclaringType().getJavaType(), attribute.getName());
    }

    public EntityPropertyModel(IModel<E> entityModel, Class<E> entityClass, String expression) {
        super(entityModel, expression);

        this.entityModel = entityModel;
        this.entityClass = entityClass;
    }

    public IModel<E> getEntityModel() {
        return entityModel;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    @Override
    public void detach() {
        super.detach();

        entityModel.detach();
    }

}
