package at.dru.wicketblog.components;

import at.dru.wicketblog.WicketWebApplication;
import at.dru.wicketblog.wicket.AuthUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

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

        add(new WebMarkupContainer("adminMenuOpener") {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible() {
                return AuthUtils.isAdmin();
            }
        });
        add(new WebMarkupContainer("adminMenu") {
            
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible() {
                return AuthUtils.isAdmin();
            }
        });

        add(new AccountInfoPanel("accountInfo").setRenderBodyOnly(true));
    }
}
