package at.dru.wicketblog.wicket.page;

import at.dru.wicketblog.WicketWebApplication;
import at.dru.wicketblog.wicket.component.BootstrapFeedbackPanel;
import at.dru.wicketblog.wicket.component.DebugInfoPanel;
import at.dru.wicketblog.wicket.component.NavigationPanel;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

public abstract class AbstractPage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    @SpringBean
    protected WicketWebApplication wicketWebApplication;

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        // Standard - Styles
        response.render(CssHeaderItem.forReference(new ContextRelativeResourceReference("css/default.css")));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new BootstrapFeedbackPanel("feedback"));
        add(new Label("windowTitle", getWindowTitle()));
        add(new Label("pageTitle", getPageTitle()));
        add(new DebugBar("debugBar"));
        add(new NavigationPanel("nav"));
        add(new DebugInfoPanel("debugInfo"));
    }

    protected String getWindowTitle() {
        return getPageTitle() + " - " + wicketWebApplication.getAppName();
    }

    protected abstract String getPageTitle();
}
