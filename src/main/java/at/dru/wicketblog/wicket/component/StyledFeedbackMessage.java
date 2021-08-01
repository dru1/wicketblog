package at.dru.wicketblog.wicket.component;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import javax.annotation.Nonnull;

public class StyledFeedbackMessage extends Panel {

    private final FeedbackMessage message;

    public StyledFeedbackMessage(@Nonnull String id, @Nonnull FeedbackMessage message) {
        super(id);

        this.message = message;

        setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new WebMarkupContainer("container") {

            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new Label("message", getMessage().getMessage()));
            }

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                switch (getMessage().getLevel()) {
                    case FeedbackMessage.SUCCESS:
                        tag.append("class", "success", " ");
                        break;
                    case FeedbackMessage.INFO:
                    case FeedbackMessage.DEBUG:
                        tag.append("class", "info", " ");
                        break;
                    case FeedbackMessage.WARNING:
                        tag.append("class", "warning", " ");
                        break;
                    case FeedbackMessage.FATAL:
                    case FeedbackMessage.ERROR:
                        tag.append("class", "error", " ");
                        break;
                    default:
                        // NOP
                }
            }

        });
    }

    @Nonnull
    private FeedbackMessage getMessage() {
        return message;
    }

}
