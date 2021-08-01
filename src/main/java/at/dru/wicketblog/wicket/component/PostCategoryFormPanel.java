package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.model.PostCategory_;
import at.dru.wicketblog.wicket.build.EntityFormComposer;
import org.apache.wicket.model.IModel;

import javax.annotation.Nonnull;

public class PostCategoryFormPanel extends EntityFormPanel<PostCategory> {

    private static final long serialVersionUID = 1L;

    public PostCategoryFormPanel(String id, IModel<PostCategory> model) {
        super(id, model, PostCategory.class);
    }

    @Override
    protected void composeForm(@Nonnull EntityForm<PostCategory> entityForm) {
        super.composeForm(entityForm);

        new EntityFormComposer<>(entityForm)
                .textField(PostCategory_.name, FieldType.TEXT_FIELD)
                .textField(PostCategory_.contentClass, FieldType.TEXT_FIELD)
                .textField(PostCategory_.iconClass, FieldType.TEXT_FIELD)
                .textField(PostCategory_.backgroundClass, FieldType.TEXT_FIELD);
    }

}
