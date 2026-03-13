package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.AbstractEntity;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class EntityFormPanel<T extends AbstractEntity> extends Panel {

    private static final long serialVersionUID = 1L;

    private final Class<T> entityClass;
    private final IModel<T> entityFormModel;

    public EntityFormPanel(String id, IModel<T> entityFormModel, Class<T> entityClass) {
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

    protected void composeForm(EntityForm<T> entityForm) {
    }

}
