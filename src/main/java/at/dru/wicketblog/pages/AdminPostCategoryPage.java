package at.dru.wicketblog.pages;

import at.dru.wicketblog.components.FormPanel;
import at.dru.wicketblog.components.ListPanel;
import at.dru.wicketblog.components.PostCategoryFormPanel;
import at.dru.wicketblog.components.PostCategoryListPanel;
import at.dru.wicketblog.model.PostCategory;
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
    protected FormPanel<PostCategory> getFormPanel(@Nonnull String wicketId, @Nonnull IModel<PostCategory> formModel) {
        return new PostCategoryFormPanel(wicketId, formModel);
    }

    @Override
    protected Class<PostCategory> getFormType() {
        return PostCategory.class;
    }
}
