package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.model.PostRepository;
import at.dru.wicketblog.model.Post_;
import at.dru.wicketblog.wicket.model.EntityPropertyModel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;

import java.util.List;

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

        add(new ListView<Post>("posts", new LoadableDetachableModel<List<Post>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected List<Post> load() {
                return postRepository.findAllByOrderByModifiedDesc(PageRequest.of(0, 20)).getContent();
            }
        }) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Post> item) {
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
