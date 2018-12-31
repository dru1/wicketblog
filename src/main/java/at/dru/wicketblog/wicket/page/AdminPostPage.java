package at.dru.wicketblog.wicket.page;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.wicket.component.EntityFormPanel;
import at.dru.wicketblog.wicket.component.ListPanel;
import at.dru.wicketblog.wicket.component.PostFormPanel;
import at.dru.wicketblog.wicket.component.PostListPanel;
import org.apache.wicket.model.IModel;

import javax.annotation.Nonnull;

public class AdminPostPage extends AbstractAdminFormPage<Post> {

    private static final long serialVersionUID = 1L;

    @Override
    protected ListPanel<Post> getListPanel(@Nonnull String wicketId) {
        return new PostListPanel(wicketId);
    }

    @Override
    protected Post newInstance() {
        Post p = super.newInstance();
        p.setAuthor(getCurrentAccount());
        return p;
    }

    @Override
    protected EntityFormPanel<Post> getFormPanel(@Nonnull String wicketId, @Nonnull IModel<Post> formModel) {
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
