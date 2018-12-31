package at.dru.wicketblog.wicket.page;

import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.wicket.component.EntityFormPanel;
import at.dru.wicketblog.wicket.component.ListPanel;
import at.dru.wicketblog.wicket.component.PostCategoryFormPanel;
import at.dru.wicketblog.wicket.component.PostCategoryListPanel;
import org.apache.wicket.model.IModel;

import javax.annotation.Nonnull;

public class AdminPostCategoryPage extends AbstractAdminFormPage<PostCategory> {

    private static final long serialVersionUID = 1L;

    @Override
    protected String getPageTitle() {
        return "Admin Post Categories";
    }

    @Override
    protected ListPanel<PostCategory> getListPanel(@Nonnull String wicketId) {
        return new PostCategoryListPanel(wicketId);
    }

    @Override
    protected EntityFormPanel<PostCategory> getFormPanel(@Nonnull String wicketId, @Nonnull IModel<PostCategory> formModel) {
        return new PostCategoryFormPanel(wicketId, formModel);
    }

    @Override
    protected Class<PostCategory> getFormType() {
        return PostCategory.class;
    }
}
