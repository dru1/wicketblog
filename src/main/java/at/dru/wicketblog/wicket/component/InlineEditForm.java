package at.dru.wicketblog.wicket.component;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import at.dru.wicketblog.wicket.model.MetaModel;

public class InlineEditForm<T, E extends DefaultEntity> extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private EntityServiceRegistry entityServiceRegistry;

    private final PropertyModel<T> formModel;

    private final Class<E> entityClass;

    private final FieldType fieldType;

    private final MetaModel<E> labelModel;

    public InlineEditForm(String id, PropertyModel<T> model, Class<E> entityClass, FieldType fieldType) {
        super(id, model);

        this.formModel = model;
        this.entityClass = entityClass;
        this.fieldType = fieldType;

        this.labelModel = new MetaModel<>(entityClass, formModel.getPropertyExpression());

        setOutputMarkupId(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<T> form = new Form<T>("editForm", formModel) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                E entity = entityClass.cast(formModel.getInnermostModelOrObject());
                entityServiceRegistry.forClass(entityClass).saveEntity(entity);
                getRequestCycle().find(AjaxRequestTarget.class).ifPresent(target -> target.add(InlineEditForm.this));
            }
            
        };

        TextField<T> titleField = new TextField<T>("inputField", formModel) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(FieldType.TEXT_FIELD.equals(fieldType));
            }
        };
        titleField.add(new AttributeAppender("placeholder", labelModel));
        titleField.setLabel(labelModel);

        TextArea<T> contentField = new TextArea<T>("textArea", formModel) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(FieldType.TEXT_AREA.equals(fieldType));
            }
        };
        contentField.add(new AttributeAppender("placeholder", labelModel));
        contentField.setLabel(labelModel);

        AjaxSubmitLink submitLink = new AjaxSubmitLink("submit", form) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onAfterSubmit(AjaxRequestTarget target) {
                super.onAfterSubmit(target);
                InlineEditForm.this.onAfterSubmit(target);
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
