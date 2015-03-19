package at.dru.wicketblog.pages;

import at.dru.wicketblog.service.AccountService;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

public class SetupPage extends AbstractPage {

    @SpringBean
    private AccountService accountService;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final ValueMap properties = new ValueMap();

        Form<ValueMap> form = new Form<ValueMap>("setupForm", Model.of(properties)) {
            @Override
            protected void onSubmit() {
                super.onSubmit();

                accountService.setupAdmin(properties.getString("username"), properties.getString("password"), properties.getBoolean("isAdmin"));

                success("Success !!!");
            }
        };

        TextField<String> username = new TextField<>("username", new PropertyModel<String>(properties, "username"));
        username.setType(String.class);
        form.add(username);

        TextField<String> password = new TextField<>("password", new PropertyModel<String>(properties, "password"));
        password.setType(String.class);
        form.add(password);

        CheckBox isAdmin = new CheckBox("isAdmin", new PropertyModel<Boolean>(properties, "isAdmin"));
        isAdmin.setType(Boolean.class);
        form.add(isAdmin);

        add(form);
    }

    @Override
    protected String getPageTitle() {
        return "Setup";
    }
}
