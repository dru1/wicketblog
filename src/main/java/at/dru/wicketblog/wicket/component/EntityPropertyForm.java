package at.dru.wicketblog.wicket.component;


import at.dru.wicketblog.model.AbstractEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import at.dru.wicketblog.wicket.behavior.VisibilityBehavior;
import at.dru.wicketblog.wicket.model.EntityPropertyModel;
import at.dru.wicketblog.wicket.model.MetaModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.annotation.Nonnull;

public class EntityPropertyForm<T, E extends AbstractEntity> extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private EntityServiceRegistry entityServiceRegistry;

    private final EntityPropertyModel<T, E> entityPropertyModel;
    private final FieldType fieldType;
    private final MetaModel<E> labelModel;

    public EntityPropertyForm(@Nonnull String id, @Nonnull EntityPropertyModel<T, E> entityPropertyModel, @Nonnull FieldType fieldType) {
        super(id, entityPropertyModel);

        this.entityPropertyModel = entityPropertyModel;
        this.fieldType = fieldType;
        this.labelModel = new MetaModel<>(this.entityPropertyModel.getEntityClass(), this.entityPropertyModel.getPropertyExpression());

        setOutputMarkupId(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<T> form = new Form<T>("editForm", entityPropertyModel) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                E entity = entityPropertyModel.getEntityModel().getObject();
                entityServiceRegistry.forClass(entityPropertyModel.getEntityClass()).saveEntity(entity);
                getRequestCycle().find(AjaxRequestTarget.class).ifPresent(target -> target.add(EntityPropertyForm.this));
            }

        };

        TextField<T> titleField = new TextField<>("inputField", entityPropertyModel);
        titleField.add(new VisibilityBehavior(() -> FieldType.TEXT_FIELD.equals(fieldType)));
        titleField.add(new AttributeAppender("placeholder", labelModel));
        titleField.setLabel(labelModel);

        TextArea<T> contentField = new TextArea<>("textArea", entityPropertyModel);
        contentField.add(new VisibilityBehavior(() -> FieldType.TEXT_AREA.equals(fieldType)));
        contentField.add(new AttributeAppender("placeholder", labelModel));
        contentField.setLabel(labelModel);

        AjaxSubmitLink submitLink = new AjaxSubmitLink("submit", form) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onAfterSubmit(AjaxRequestTarget target) {
                super.onAfterSubmit(target);

                EntityPropertyForm.this.onAfterSubmit(target);
            }

        };

        form.add(contentField);
        form.add(titleField);
        form.add(submitLink);
        add(form);
    }

    protected void onAfterSubmit(AjaxRequestTarget target) {
    }
}
