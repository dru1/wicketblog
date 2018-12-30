package at.dru.wicketblog.behaviors;

import javax.annotation.Nonnull;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.IModel;

public class VisibilityBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    private final IModel<Boolean> visibilityModel;

    public VisibilityBehavior(@Nonnull IModel<Boolean> visibilityModel) {
        this.visibilityModel = visibilityModel;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupPlaceholderTag(true);
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        boolean visible = visibilityModel.getObject();
        component.setVisibilityAllowed(visible);
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        visibilityModel.detach();
    }

}