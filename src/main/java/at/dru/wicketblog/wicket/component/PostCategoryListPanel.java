package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.wicket.model.EntityListModel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

public class PostCategoryListPanel extends ListPanel<PostCategory> {

    private static final long serialVersionUID = 1L;

    public PostCategoryListPanel(String id) {
        super(id, PostCategory.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ListView<PostCategory>("categories", new EntityListModel<>(PostCategory.class)) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<PostCategory> item) {
                item.add(new EditableText<>("name", new PropertyModel<>(item.getModel(), "name"), FieldType.TEXT_FIELD, PostCategory.class));
                item.add(new EditableText<>("contentClass", new PropertyModel<>(item.getModel(), "contentClass"), FieldType.TEXT_FIELD, PostCategory.class));
                item.add(new EditableText<>("iconClass", new PropertyModel<>(item.getModel(), "iconClass"), FieldType.TEXT_FIELD, PostCategory.class));
                item.add(new EditableText<>("backgroundClass", new PropertyModel<>(item.getModel(), "backgroundClass"), FieldType.TEXT_FIELD, PostCategory.class));
            }

        });
    }


}
