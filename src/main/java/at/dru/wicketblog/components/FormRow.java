package at.dru.wicketblog.components;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.ILabelProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class FormRow extends Border implements ILabelProvider<String> {

    public FormRow(String id) {
        super(id);

        setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Component label = new Label("label", getLabel());

        addToBorder(label);
    }
}
