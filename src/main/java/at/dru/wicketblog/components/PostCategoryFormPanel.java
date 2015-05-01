package at.dru.wicketblog.components;

import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.wicket.EntityFormBuilder;
import org.apache.wicket.model.IModel;

public class PostCategoryFormPanel extends FormPanel<PostCategory> {

    public PostCategoryFormPanel(String id, IModel<PostCategory> model) {
        super(id, model, PostCategory.class);
        setFormType(FormType.HORIZONTAL);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        new EntityFormBuilder<>(entityClass, formModel)
                .withTextField("name", FieldType.TEXT_FIELD)
                .withTextField("contentClass", FieldType.TEXT_FIELD)
                .withTextField("iconClass", FieldType.TEXT_FIELD)
                .withTextField("backgroundClass", FieldType.TEXT_FIELD)
                .build(entityForm);
    }
}
