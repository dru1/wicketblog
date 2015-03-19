package at.dru.wicketblog.components;

import at.dru.wicketblog.wicket.CurrentAuthenticatedWebSession;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

public class LoginFormPanel extends Panel {

    public LoginFormPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final ValueMap properties = new ValueMap();

        Form<ValueMap> form = new StatelessForm<ValueMap>("loginForm", Model.of(properties)) {
            @Override
            protected void onSubmit() {
                super.onSubmit();

                // Login
                boolean authResult = CurrentAuthenticatedWebSession.get().signIn(properties.getString("username"), properties.getString("password"));
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

        TextField<String> username = new TextField<>("username", new PropertyModel<String>(properties, "username"));
        username.setType(String.class);
        form.add(username);

        PasswordTextField password = new PasswordTextField("password", new PropertyModel<String>(properties, "password"));
        password.setType(String.class);
        form.add(password);

        add(form);
    }

    protected String getFormCssClass() {
        return "form";
    }
}
