package at.dru.wicketblog.wicket.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;

import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.wicket.security.AuthUtils;
import at.dru.wicketblog.wicket.builder.FormBuilder;
import at.dru.wicketblog.wicket.model.MetaModel;

public class LoginFormPanel extends Panel {

    private static final long serialVersionUID = 1L;

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
        Form<ValueMap> form = new Form<ValueMap>("loginForm", formModel) {
            
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                super.onSubmit();

                // Login
                boolean result = AuthUtils.signIn(properties.getString("username"), properties.getString("password"));
                if (result) {
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
