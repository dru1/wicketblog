package at.dru.wicketblog.wicket.page;

import at.dru.wicketblog.wicket.component.PostListPanel;

public class HomePage extends AbstractPage {

    private static final long serialVersionUID = 1L;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new PostListPanel("newsList"));
    }

    @Override
    protected String getPageTitle() {
        return "Home";
    }
}
