package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.DefaultEntity;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import javax.annotation.Nonnull;

public class EntityFormPanel<T extends DefaultEntity> extends Panel {

    private static final long serialVersionUID = 1L;

    private final Class<T> entityClass;
    private final IModel<T> entityFormModel;

    public EntityFormPanel(@Nonnull String id, @Nonnull IModel<T> entityFormModel, @Nonnull Class<T> entityClass) {
        super(id, entityFormModel);

        this.entityFormModel = entityFormModel;
        this.entityClass = entityClass;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        EntityForm<T> entityForm = new EntityForm<T>("form", entityFormModel, entityClass);
        composeForm(entityForm);
        add(entityForm);
    }

    protected void composeForm(@Nonnull EntityForm<T> entityForm) {
    }

}
