package at.dru.wicketblog.components;

import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.model.PostCategoryRepository;
import com.google.common.collect.Lists;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class PostCategoryListPanel extends ListPanel<PostCategory> {

    @SpringBean
    private PostCategoryRepository postCategoryRepository;

    public PostCategoryListPanel(String id) {
        super(id, PostCategory.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ListView<PostCategory>("categories", new LoadableDetachableModel<List<? extends PostCategory>>() {
            @Override
            protected List<? extends PostCategory> load() {
                return Lists.newArrayList(postCategoryRepository.findAll());
            }
        }) {
            @Override
            protected void populateItem(ListItem<PostCategory> item) {
                item.add(new EditableText<>("name", new PropertyModel<String>(item.getModel(), "name"), FieldType.INPUT_FIELD, PostCategory.class));
                item.add(new EditableText<>("contentClass", new PropertyModel<String>(item.getModel(), "contentClass"), FieldType.INPUT_FIELD, PostCategory.class));
                item.add(new EditableText<>("iconClass", new PropertyModel<String>(item.getModel(), "iconClass"), FieldType.INPUT_FIELD, PostCategory.class));
                item.add(new EditableText<>("backgroundClass", new PropertyModel<String>(item.getModel(), "backgroundClass"), FieldType.INPUT_FIELD, PostCategory.class));
            }
        });
    }


}
