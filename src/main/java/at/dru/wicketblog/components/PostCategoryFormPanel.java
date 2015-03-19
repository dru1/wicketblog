package at.dru.wicketblog.components;

import at.dru.wicketblog.model.PostCategory;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class PostCategoryFormPanel extends FormPanel<PostCategory> {

    public PostCategoryFormPanel(String id, IModel<PostCategory> model) {
        super(id, model, PostCategory.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        TextField<String> nameField = new TextField<>("name", new PropertyModel<String>(formModel, "name"));
        entityForm.add(nameField);

        TextField<String> contentClassField = new TextField<>("contentClass", new PropertyModel<String>(formModel, "contentClass"));
        entityForm.add(contentClassField);

        TextField<String> iconClassField = new TextField<>("iconClass", new PropertyModel<String>(formModel, "iconClass"));
        entityForm.add(iconClassField);

        TextField<String> backgroundClassField = new TextField<>("backgroundClass", new PropertyModel<String>(formModel, "backgroundClass"));
        entityForm.add(backgroundClassField);

        add(entityForm);
    }
}
