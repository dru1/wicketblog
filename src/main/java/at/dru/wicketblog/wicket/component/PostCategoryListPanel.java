package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.model.PostCategory_;
import at.dru.wicketblog.wicket.model.EntityListModel;
import at.dru.wicketblog.wicket.model.EntityPropertyModel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

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
                item.add(new EditableText<>("name", new EntityPropertyModel<>(item.getModel(), PostCategory_.name)));
                item.add(new EditableText<>("contentClass", new EntityPropertyModel<>(item.getModel(), PostCategory_.contentClass)));
                item.add(new EditableText<>("iconClass", new EntityPropertyModel<>(item.getModel(), PostCategory_.iconClass)));
                item.add(new EditableText<>("backgroundClass", new EntityPropertyModel<>(item.getModel(), PostCategory_.backgroundClass)));
            }

        });
    }


}
