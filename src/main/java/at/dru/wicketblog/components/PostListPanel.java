package at.dru.wicketblog.components;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.model.PostRepository;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class PostListPanel extends ListPanel<Post> {

    @SpringBean
    private PostRepository postRepository;

    public PostListPanel(String id) {
        super(id, Post.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ListView<Post>("posts", new LoadableDetachableModel<List<? extends Post>>() {
            @Override
            protected List<? extends Post> load() {
                return postRepository.findAllByOrderByModifiedDesc(new PageRequest(0, 20)).getContent();
            }
        }) {
            @Override
            protected void populateItem(ListItem<Post> item) {
                item.add(new EditableText<>("title", new PropertyModel<String>(item.getModel(), "title"), FieldType.INPUT_FIELD, Post.class));
                item.add(new EditableText<>("content", new PropertyModel<String>(item.getModel(), "content"), FieldType.TEXT_AREA, Post.class));
            }
        });
    }
}
