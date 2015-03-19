package at.dru.wicketblog.components;

import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.annotation.Nonnull;

public class EntityForm<T extends DefaultEntity> extends Form<T> {
    @SpringBean
    protected EntityServiceRegistry entityServiceRegistry;

    protected final Class<T> entityClass;

    protected final IModel<T> formModel;

    public EntityForm(String id, IModel<T> model, @Nonnull Class<T> entityClass) {
        super(id, model);
        this.entityClass = entityClass;
        this.formModel = model;
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();

        T entity = formModel.getObject();
        entityServiceRegistry.forClass(entityClass).saveEntity(entity);
        success(entityClass.getSimpleName() + " created/updated with id: " + entity.getId());
    }
}
