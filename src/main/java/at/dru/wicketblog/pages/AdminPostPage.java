package at.dru.wicketblog.pages;

import at.dru.wicketblog.components.FormPanel;
import at.dru.wicketblog.components.ListPanel;
import at.dru.wicketblog.components.PostFormPanel;
import at.dru.wicketblog.components.PostListPanel;
import at.dru.wicketblog.model.Post;
import org.apache.wicket.model.IModel;

import javax.annotation.Nonnull;

public class AdminPostPage extends AbstractAdminFormPage<Post> {

    @Override
    protected ListPanel<Post> getListPanel(@Nonnull String wicketId) {
        return new PostListPanel(wicketId);
    }

    @Override
    protected Post newInstance() throws IllegalAccessException, InstantiationException {
        Post p = super.newInstance();
        p.setAuthor(getCurrentAccount());
        return p;
    }

    @Override
    protected FormPanel<Post> getFormPanel(@Nonnull String wicketId, @Nonnull IModel<Post> formModel) {
        return new PostFormPanel(wicketId, formModel);
    }

    @Override
    protected Class<Post> getFormType() {
        return Post.class;
    }

    @Override
    protected String getPageTitle() {
        return "Admin Posts";
    }
}
