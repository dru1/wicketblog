package at.dru.wicketblog.components;


import javax.annotation.Nonnull;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.wicket.AuthUtils;

public class EditableText<T, E extends DefaultEntity> extends Panel {

    private static final long serialVersionUID = 1L;

    private final Class<E> entityClass;
    private final PropertyModel<T> formModel;
    private final FieldType fieldType;

    private IModel<ViewMode> viewMode;

    public EditableText(@Nonnull String id,
                        @Nonnull PropertyModel<T> formModel,
                        @Nonnull FieldType fieldType,
                        @Nonnull Class<E> entityClass) {
        super(id, formModel);

        this.fieldType = fieldType;
        this.formModel = formModel;
        this.entityClass = entityClass;

        this.viewMode = new LoadableDetachableModel<ViewMode>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected ViewMode load() {
                return ViewMode.MODE_VIEW;
            }
        };
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        final Component viewEdit;
        if (ViewMode.MODE_EDIT.equals(viewMode.getObject())) {
            viewEdit = new InlineEditForm<T, E>("viewEdit", formModel, entityClass, fieldType) {

                private static final long serialVersionUID = 1L;

                @Override
                protected void onAfterSubmit(AjaxRequestTarget target) {
                    super.onAfterSubmit(target);
                    viewMode.detach();
                    target.add(EditableText.this);
                }
            };
        } else {
            viewEdit = new MultiLineLabel("viewEdit", formModel);
        }

        viewEdit.setRenderBodyOnly(true);
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
                toggleMode();
                target.add(EditableText.this);
            }

            @Override
            public boolean isVisible() {
                return canToggle();
            }
        }.setOutputMarkupPlaceholderTag(true));
    }

    private void toggleMode() {
        if (canToggle()) {
            viewMode.setObject(ViewMode.MODE_VIEW.equals(viewMode.getObject()) ? ViewMode.MODE_EDIT : ViewMode.MODE_VIEW);
        }
    }

    private boolean canToggle() {
        return AuthUtils.isAdmin();
    }


}
