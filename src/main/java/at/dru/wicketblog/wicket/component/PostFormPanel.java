package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.model.Post_;
import at.dru.wicketblog.wicket.build.EntityFormComposer;
import at.dru.wicketblog.wicket.model.EntityListModel;
import org.apache.wicket.model.IModel;

import javax.annotation.Nonnull;

public class PostFormPanel extends EntityFormPanel<Post> {

    private static final long serialVersionUID = 1L;

    public PostFormPanel(@Nonnull String id, @Nonnull IModel<Post> model) {
        super(id, model, Post.class);

        setFormType(FormType.HORIZONTAL);
    }

    @Override
    protected void composeForm(@Nonnull EntityForm<Post> entityForm) {
        super.composeForm(entityForm);

        new EntityFormComposer<>(entityForm)
                .textField(Post_.title, FieldType.TEXT_FIELD)
                .textField(Post_.content, FieldType.TEXT_AREA)
                .entityChoice(Post_.postCategory, new EntityListModel<>(PostCategory.class));
    }
}
