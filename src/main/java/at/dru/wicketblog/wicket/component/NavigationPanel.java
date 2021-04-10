package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.WicketWebApplication;
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
        add(new AccountInfoPanel("accountInfo").setRenderBodyOnly(true));
    }

}
