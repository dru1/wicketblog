package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.AbstractEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.annotation.Nonnull;

public class EntityForm<T extends AbstractEntity> extends Form<T> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    protected EntityServiceRegistry entityServiceRegistry;

    private final Class<T> entityClass;
    private final IModel<T> formModel;

    public EntityForm(@Nonnull String id, @Nonnull IModel<T> model, @Nonnull Class<T> entityClass) {
        super(id, model);

        this.entityClass = entityClass;
        this.formModel = model;
    }

    @Nonnull
    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();

        T entity = formModel.getObject();
        entityServiceRegistry.forClass(entityClass).saveEntity(entity);
        success(entityClass.getSimpleName() + " created/updated with id: " + entity.getId());
    }

}
