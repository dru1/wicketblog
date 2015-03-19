package at.dru.wicketblog.components;


import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import at.dru.wicketblog.wicket.AuthUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.annotation.Nonnull;

public class EditableText<T, E extends DefaultEntity> extends Panel {

    @SpringBean
    private EntityServiceRegistry entityServiceRegistry;

    private final Class<E> entityClass;
    private final PropertyModel<T> formModel;
    private final FieldType fieldType;

    private ViewMode viewMode;

    public EditableText(@Nonnull String id, @Nonnull PropertyModel<T> formModel, @Nonnull FieldType fieldType, @Nonnull Class<E> entityClass) {
        super(id, formModel);
        this.fieldType = fieldType;
        this.formModel = formModel;
        this.entityClass = entityClass;

        this.viewMode = ViewMode.MODE_VIEW;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true);

        Form<T> form = new Form<T>("editForm", formModel) {
            @Override
            public boolean isVisible() {
                return ViewMode.MODE_EDIT.equals(viewMode);
            }

            protected void onSubmit() {
                E entity = (E) formModel.getInnermostModelOrObject();
                entityServiceRegistry.forClass(entityClass).saveEntity(entity);
                success(entityClass.getSimpleName() + " updated with id: " + entity.getId());
                final AjaxRequestTarget target = getRequestCycle().find(AjaxRequestTarget.class);
                if (target != null) {
                    target.add(EditableText.this);
                }
            }
        };

        TextField<T> titleField = new TextField<T>("inputField", formModel) {
            @Override
            public boolean isVisible() {
                return FieldType.INPUT_FIELD.equals(fieldType);
            }
        };
        form.add(titleField);

        TextArea<T> contentField = new TextArea<T>("textArea", formModel) {
            @Override
            public boolean isVisible() {
                return FieldType.TEXT_AREA.equals(fieldType);
            }
        };
        form.add(contentField);

        add(form);

        Label view = new Label("view", formModel) {
            @Override
            public boolean isVisible() {
                return ViewMode.MODE_VIEW.equals(viewMode);
            }
        };
        add(view);

        add(new AjaxLink<Void>("toggle") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                toggleMode();
                target.add(EditableText.this);
            }

            @Override
            public boolean isVisible() {
                return canToggle();
            }
        });
    }

    private void toggleMode() {
        if (canToggle()) {
            viewMode = ViewMode.MODE_VIEW.equals(viewMode) ? ViewMode.MODE_EDIT : ViewMode.MODE_VIEW;
        }
    }

    private boolean canToggle() {
        return AuthUtils.isAdmin();
    }


}
