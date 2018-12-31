package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.Account_;
import at.dru.wicketblog.wicket.build.FormComposer;
import at.dru.wicketblog.wicket.model.MetaModel;
import at.dru.wicketblog.wicket.security.AuthUtils;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ChainingModel;

import javax.annotation.Nonnull;

public class LoginFormPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private FormType formType;

    private transient String username;
    private transient String password;

    public LoginFormPanel(@Nonnull String id) {
        super(id);

        this.formType = FormType.DEFAULT;
    }

    @Nonnull
    public LoginFormPanel setFormType(@Nonnull FormType formType) {
        this.formType = formType;
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<Void> form = new Form<Void>("loginForm", new ChainingModel<>(this)) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                super.onSubmit();

                // Login
                boolean result = AuthUtils.signIn(username, password);
                if (result) {
                    continueToOriginalDestination();
                }
            }
        };
        composeForm(form);
        add(form);
    }

    protected void composeForm(@Nonnull Form<Void> loginForm) {
        loginForm.add(new AttributeAppender("class", getFormCssClass(), " "));

        new FormComposer<>(loginForm)
                .textField("username", FieldType.TEXT_FIELD, new MetaModel<>(Account_.login))
                .textField("password", FieldType.PASSWORD_FIELD, new MetaModel<>(Account_.password));
    }

    @Nonnull
    protected String getFormCssClass() {
        return formType.getCssClass();
    }
}
