package at.dru.wicketblog.components;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.wicket.EntityFormBuilder;
import org.apache.wicket.model.IModel;

public class PostFormPanel extends FormPanel<Post> {

    public PostFormPanel(String id, IModel<Post> model) {
        super(id, model, Post.class);
        setFormType(FormType.HORIZONTAL);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        new EntityFormBuilder<>(entityClass, formModel)
                .withTextField("title", FieldType.TEXT_FIELD)
                .withTextField("content", FieldType.TEXT_AREA)
                .withSingleChoice("postCategory", PostCategory.class)
                .build(entityForm);
    }
}
