package at.dru.wicketblog.components;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.model.PostCategory;
import com.google.common.collect.Lists;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import java.util.List;

public class PostFormPanel extends FormPanel<Post> {

    public PostFormPanel(String id, IModel<Post> model) {
        super(id, model, Post.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        TextField<String> titleField = new TextField<>("title", new PropertyModel<String>(formModel, "title"));
        entityForm.add(titleField);

        TextArea<String> contentField = new TextArea<>("content", new PropertyModel<String>(formModel, "content"));
        entityForm.add(contentField);

        DropDownChoice<PostCategory> postCategorySelect = new DropDownChoice<>("postCategory",
                new PropertyModel<PostCategory>(formModel, "postCategory"),
                new LoadableDetachableModel<List<? extends PostCategory>>() {
                    @Override
                    protected List<? extends PostCategory> load() {
                        return Lists.newArrayList(entityServiceRegistry.forClass(PostCategory.class).findAll());
                    }
                }
        );
        entityForm.add(postCategorySelect);
    }
}
