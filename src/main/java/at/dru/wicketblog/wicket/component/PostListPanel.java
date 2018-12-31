package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.model.PostRepository;
import at.dru.wicketblog.model.Post_;
import at.dru.wicketblog.wicket.model.EntityDataProvider;
import at.dru.wicketblog.wicket.model.EntityPropertyModel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PostListPanel extends ListPanel<Post> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PostRepository postRepository;

    public PostListPanel(String id) {
        super(id, Post.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new DataView<Post>("posts", new EntityDataProvider<>(Post.class, postRepository::findAllByOrderByModifiedDesc)) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(Item<Post> item) {
                item.add(new EditableText<>("title", new EntityPropertyModel<>(item.getModel(), Post_.title))
                        .cssClasses("blog-title"));
                item.add(new EditableText<>("content", new EntityPropertyModel<>(item.getModel(), Post_.content))
                        .fieldType(FieldType.TEXT_AREA)
                        .cssClasses("blog-content")
                        .enableMarkdown(true));
            }
        });
    }
}
