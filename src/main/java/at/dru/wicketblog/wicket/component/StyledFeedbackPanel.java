package at.dru.wicketblog.wicket.component;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class StyledFeedbackPanel extends FeedbackPanel {

    private static final long serialVersionUID = 1L;

    public StyledFeedbackPanel(String id) {
        super(id);
    }

    @Override
    protected Component newMessageDisplayComponent(String id, FeedbackMessage message) {
        return new StyledFeedbackMessage(id, message);
    }

}
