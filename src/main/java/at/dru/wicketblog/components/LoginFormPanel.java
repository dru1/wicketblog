package at.dru.wicketblog.components;

import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.wicket.FormBuilder;
import at.dru.wicketblog.wicket.CurrentAuthenticatedWebSession;
import at.dru.wicketblog.wicket.MetaModel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;

public class LoginFormPanel extends Panel {

    private FormType formType;

    public LoginFormPanel(String id) {
        super(id);
        this.formType = FormType.DEFAULT;
    }

    public LoginFormPanel setFormType(FormType formType) {
        this.formType = formType;
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final ValueMap properties = new ValueMap();

        IModel<ValueMap> formModel = Model.of(properties);
        Form<ValueMap> form = new StatelessForm<ValueMap>("loginForm", formModel) {
            @Override
            protected void onSubmit() {
                super.onSubmit();

                // Login
                boolean authResult = CurrentAuthenticatedWebSession.get().signIn(
                        properties.getString("username"), properties.getString("password"));

                if (authResult) {
                    continueToOriginalDestination();
                }
            }

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                tag.put("class", getFormCssClass());
            }
        };

        new FormBuilder<>(formModel)
                .withTextField("username", FieldType.TEXT_FIELD, new MetaModel<>(Account.class, "username"))
                .withTextField("password", FieldType.PASSWORD_FIELD, new MetaModel<>(Account.class, "password"))
                .build(form);

        add(form);
    }

    protected String getFormCssClass() {
        return formType.getCssClass();
    }
}
