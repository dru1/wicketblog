package at.dru.wicketblog.wicket.build;

import at.dru.wicketblog.wicket.component.FieldType;
import at.dru.wicketblog.wicket.component.FormRow;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import javax.annotation.Nonnull;

public class FormComposer<E> {

    private final Form<E> form;

    public FormComposer(@Nonnull Form<E> form) {
        this.form = form;
    }

    @Nonnull
    public FormComposer<E> textField(@Nonnull String fieldName, @Nonnull FieldType fieldType, @Nonnull IModel<String> labelModel) {
        final PropertyModel<String> fieldModel = new PropertyModel<>(form.getModel(), fieldName);

        FormRow formComponent = new FormRow(fieldName, labelModel);
        switch (fieldType) {
            case PASSWORD_FIELD:
                formComponent.add(new PasswordTextField(fieldName, fieldModel)
                        .setLabel(labelModel)
                        .add(new AttributeAppender("placeholder", labelModel)));
                break;
            case TEXT_AREA:
                formComponent.add(new TextArea<>(fieldName, fieldModel)
                        .setLabel(labelModel)
                        .add(new AttributeAppender("placeholder", labelModel)));
                break;
            case TEXT_FIELD:
            default:
                formComponent.add(new TextField<>(fieldName, fieldModel)
                        .setLabel(labelModel)
                        .add(new AttributeAppender("placeholder", labelModel)));
                break;
        }
        form.add(formComponent);
        return this;
    }

    @Nonnull
    public FormComposer<E> formRow(@Nonnull FormRow formRow) {
        form.add(formRow);
        return this;
    }

}
