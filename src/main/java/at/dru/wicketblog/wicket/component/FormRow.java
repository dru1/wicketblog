package at.dru.wicketblog.wicket.component;

import javax.annotation.Nonnull;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.ILabelProvider;
import org.apache.wicket.model.IModel;

public class FormRow extends Border implements ILabelProvider<String> {

    private static final long serialVersionUID = 1L;

    private final IModel<String> labelModel;

    public FormRow(@Nonnull String id, @Nonnull IModel<String> labelModel) {
        super(id, labelModel);

        this.labelModel = labelModel;
        setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        addToBorder(new Label("label", getLabel()));
    }

    @Override
    public IModel<String> getLabel() {
        return labelModel;
    }
}
