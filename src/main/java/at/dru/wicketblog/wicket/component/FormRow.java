package at.dru.wicketblog.wicket.component;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.ILabelProvider;

public abstract class FormRow extends Border implements ILabelProvider<String> {

    private static final long serialVersionUID = 1L;

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
