package at.dru.wicketblog.components;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class BootstrapFeedbackPanel extends FeedbackPanel {
    
    private static final long serialVersionUID = 1L;

    public BootstrapFeedbackPanel(String id) {
        super(id);
    }

    @Override
    protected String getCSSClass(FeedbackMessage message) {
        StringBuilder cssClass = new StringBuilder("alert");

        switch (message.getLevel()) {
            case FeedbackMessage.SUCCESS:
                cssClass.append(" alert-success");
                break;
            case FeedbackMessage.INFO:
            case FeedbackMessage.DEBUG:
                cssClass.append(" alert-info");
                break;
            case FeedbackMessage.WARNING:
                cssClass.append(" alert-warning");
                break;
            case FeedbackMessage.FATAL:
            case FeedbackMessage.ERROR:
                cssClass.append(" alert-danger");
                break;
        }

        return cssClass.toString();
    }
}
