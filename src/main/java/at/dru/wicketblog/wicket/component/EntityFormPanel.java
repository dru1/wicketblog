package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.DefaultEntity;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import javax.annotation.Nonnull;

public class EntityFormPanel<T extends DefaultEntity> extends Panel {

    private static final long serialVersionUID = 1L;

    private final Class<T> entityClass;
    private final IModel<T> formModel;

    private FormType formType;

    public EntityFormPanel(@Nonnull String id, @Nonnull IModel<T> entityFormModel, @Nonnull Class<T> entityClass) {
        super(id, entityFormModel);

        this.formModel = entityFormModel;
        this.entityClass = entityClass;
        this.formType = FormType.DEFAULT;
    }

    @Nonnull
    public EntityFormPanel<T> setFormType(@Nonnull FormType formType) {
        this.formType = formType;
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        EntityForm<T> entityForm = new EntityForm<T>("form", formModel, entityClass);
        composeForm(entityForm);
        add(entityForm);
    }

    protected void composeForm(@Nonnull EntityForm<T> entityForm) {
        entityForm.add(new AttributeAppender("class", getFormCssClass(), " "));
    }

    @Nonnull
    protected String getFormCssClass() {
        return formType.getCssClass();
    }
}
