package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.model.PostRepository;
import at.dru.wicketblog.model.Post_;
import at.dru.wicketblog.service.DefaultsService;
import at.dru.wicketblog.wicket.model.EntityDataProvider;
import at.dru.wicketblog.wicket.model.EntityPropertyModel;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PostListPanel extends ListPanel<Post> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PostRepository postRepository;

    @SpringBean
    private DefaultsService defaultsService;

    public PostListPanel(String id) {
        super(id, Post.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        int pageSize = defaultsService.getPageSize();
        EntityDataProvider<Post> postsDataProvider = new EntityDataProvider<>(Post.class, postRepository::findAllByOrderByModifiedDesc, pageSize);
        DataView<Post> postsDataView = new DataView<Post>("posts", postsDataProvider, pageSize) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(Item<Post> item) {
                item.add(new EditableText<>("title", new EntityPropertyModel<>(item.getModel(), Post_.title))
                        .cssClasses("item-title"));
                item.add(new EditableText<>("content", new EntityPropertyModel<>(item.getModel(), Post_.content))
                        .fieldType(FieldType.TEXT_AREA)
                        .cssClasses("item-content")
                        .enableMarkdown(true));
            }
        };

        PagingNavigator postsPagingNavigator = new PagingNavigator("paging", postsDataView);
        queue(postsDataView, postsPagingNavigator);
    }

}
