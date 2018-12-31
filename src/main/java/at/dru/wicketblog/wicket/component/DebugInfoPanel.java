package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.WebApplication;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.time.ZonedDateTime;

public class DebugInfoPanel extends Panel {

    private static final long serialVersionUID = 1L;
    
    @SpringBean
    private WebApplication webApplication;

    public DebugInfoPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("now", ZonedDateTime.now()));
        add(new Label("startup", webApplication.getStartup()));
        add(new Label("appName", webApplication.getAppName()));
    }
}
