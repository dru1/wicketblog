package at.dru.wicketblog.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import at.dru.wicketblog.WicketWebApplication;

public class NavigationPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private WicketWebApplication wicketWebApplication;

    public NavigationPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("appName", wicketWebApplication.getAppName()));
        add(new AccountInfoPanel("accountInfo").setRenderBodyOnly(true));
    }
}
