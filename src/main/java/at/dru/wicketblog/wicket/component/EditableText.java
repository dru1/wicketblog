package at.dru.wicketblog.wicket.component;


import at.dru.wicketblog.model.AbstractEntity;
import at.dru.wicketblog.wicket.behavior.ShowdownBehavior;
import at.dru.wicketblog.wicket.behavior.VisibilityBehavior;
import at.dru.wicketblog.wicket.model.EntityPropertyModel;
import at.dru.wicketblog.wicket.security.AuthUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import javax.annotation.Nonnull;

public class EditableText<T, E extends AbstractEntity> extends Panel {

    private static final long serialVersionUID = 1L;

    private final EntityPropertyModel<T, E> entityPropertyModel;
    private ViewMode viewMode;

    private FieldType fieldType;
    private boolean enableMarkdown;
    private String cssClasses;

    public EditableText(@Nonnull String id, @Nonnull EntityPropertyModel<T, E> entityPropertyModel) {
        super(id, entityPropertyModel);

        this.entityPropertyModel = entityPropertyModel;

        resetViewMode();
        fieldType(FieldType.TEXT_FIELD);
    }

    @Nonnull
    public EditableText<T, E> fieldType(@Nonnull FieldType fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    @Nonnull
    public EditableText<T, E> enableMarkdown(boolean enableMarkdown) {
        this.enableMarkdown = enableMarkdown;
        return this;
    }

    @Nonnull
    public EditableText<T, E> cssClasses(@Nonnull String cssClasses) {
        this.cssClasses = cssClasses;
        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        final Component viewEdit;
        if (ViewMode.MODE_EDIT.equals(viewMode)) {
            viewEdit = new EntityPropertyForm<T, E>("viewEdit", entityPropertyModel, fieldType) {

                private static final long serialVersionUID = 1L;

                @Override
                protected void onAfterSubmit(AjaxRequestTarget target) {
                    super.onAfterSubmit(target);

                    resetViewMode();
                    target.add(EditableText.this);
                }
            };
        } else {
            viewEdit = new Label("viewEdit", entityPropertyModel);

            if (enableMarkdown) {
                viewEdit.add(new ShowdownBehavior()
                        .option("tables", "true")
                        .option("tasklists", "true")
                );
            }
        }

        if (cssClasses != null) {
            viewEdit.add(new AttributeAppender("class", cssClasses, " "));
        }

        addOrReplace(viewEdit);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true);

        add(new AjaxLink<Void>("toggle") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                toggleViewMode();
                target.add(EditableText.this);
            }

        }.add(new VisibilityBehavior(this::canToggle)));
    }

    private void resetViewMode() {
        viewMode = ViewMode.MODE_VIEW;
    }

    private void toggleViewMode() {
        viewMode = ViewMode.MODE_VIEW.equals(viewMode) ? ViewMode.MODE_EDIT : ViewMode.MODE_VIEW;
    }

    private boolean canToggle() {
        return AuthUtils.isAdmin();
    }

}
