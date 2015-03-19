package at.dru.wicketblog.pages;

import at.dru.wicketblog.components.PostListPanel;

public class HomePage extends AbstractPage {

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
