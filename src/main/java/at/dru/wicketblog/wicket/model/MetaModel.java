package at.dru.wicketblog.wicket.model;

import at.dru.wicketblog.model.AbstractEntity;
import at.dru.wicketblog.service.MetaModelService;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.annotation.Nonnull;
import javax.persistence.metamodel.Attribute;

public class MetaModel<T extends AbstractEntity> implements IModel<String> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private MetaModelService metaModelService;

    private final Class<T> entityClass;

    private final String property;

    private transient String modelObject;

    private boolean attached = false;

    public MetaModel(@Nonnull Attribute<T, ?> jpaAttribute) {
        this(jpaAttribute.getDeclaringType().getJavaType(), jpaAttribute.getName());
    }

    public MetaModel(@Nonnull Class<T> entityClass, @Nonnull String property) {
        Injector.get().inject(this);

        this.entityClass = entityClass;
        this.property = property;
    }

    @Override
    public String getObject() {
        if (!attached) {
            modelObject = metaModelService.getFieldName(entityClass, property);
            attached = true;
        }
        return modelObject;
    }

    @Override
    public void setObject(String object) {
        modelObject = object;
        attached = true;
    }

    @Override
    public void detach() {
        modelObject = null;
        attached = false;
    }
}
