package at.dru.wicketblog.wicket;

import at.dru.wicketblog.components.FieldType;
import at.dru.wicketblog.components.FormRow;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

public class FormBuilder<E> extends AbstractFormBuilder<E> {

    private static final long serialVersionUID = 1L;

    private final IModel<E> formModel;

    public FormBuilder(IModel<E> formModel) {
        this(formModel, new ArrayList<Component>());
    }

    public FormBuilder(IModel<E> formModel, List<Component> componentList) {
        super(componentList);
        this.formModel = formModel;
    }

    public FormBuilder<E> withTextField(String fieldName, FieldType fieldType) {
        return withTextField(fieldName, fieldType, null);
    }

    public FormBuilder<E> withTextField(final String fieldName, FieldType fieldType, final IModel<String> labelModel) {
        final PropertyModel<String> fieldModel = new PropertyModel<>(formModel, fieldName);

        FormRow formComponent;

        switch (fieldType) {
            case PASSWORD_FIELD:
                formComponent = new FormRow(fieldName) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public IModel<String> getLabel() {
                        return labelModel;
                    }

                };
                formComponent.getBodyContainer().add(new PasswordTextField(fieldName, fieldModel)
                        .setLabel(labelModel)
                        .add(new AttributeAppender("placeholder", labelModel)));
                break;
            case TEXT_AREA:
                formComponent = new FormRow(fieldName) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public IModel<String> getLabel() {
                        return labelModel;
                    }

                };
                formComponent.getBodyContainer().add(new TextArea<>(fieldName, fieldModel)
                        .setLabel(labelModel)
                        .add(new AttributeAppender("placeholder", labelModel)));
                break;
            case TEXT_FIELD:
            default:
                formComponent = new FormRow(fieldName) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public IModel<String> getLabel() {
                        return labelModel;
                    }
                    
                };
                formComponent.getBodyContainer().add(new TextField<>(fieldName, fieldModel)
                        .setLabel(labelModel)
                        .add(new AttributeAppender("placeholder", labelModel)));
                break;
        }
        componentList.add(formComponent);
        return this;
    }


}
