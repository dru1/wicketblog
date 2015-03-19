package at.dru.wicketblog.components;

import at.dru.wicketblog.WicketWebApplication;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Date;

public class DebugInfoPanel extends Panel {

    @SpringBean
    private WicketWebApplication wicketWebApplication;

    public DebugInfoPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("now", new Date()));
        add(new Label("startup", wicketWebApplication.getStartup()));
        add(new Label("appName", wicketWebApplication.getAppName()));
    }
}
