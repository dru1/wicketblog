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

public class FormComposer<E> {

    private final Form<E> form;

    public FormComposer(Form<E> form) {
        this.form = form;
    }

    public FormComposer<E> textField(String fieldName, FieldType fieldType, IModel<String> labelModel) {
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

    public FormComposer<E> formRow(FormRow formRow) {
        form.add(formRow);
        return this;
    }

}
