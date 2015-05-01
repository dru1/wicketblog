package at.dru.wicketblog.components;


import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import at.dru.wicketblog.wicket.MetaModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class InlineEditForm<T, E extends DefaultEntity> extends Panel {

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
            @Override
            protected void onSubmit() {
                E entity = (E) formModel.getInnermostModelOrObject();
                entityServiceRegistry.forClass(entityClass).saveEntity(entity);
                final AjaxRequestTarget target = getRequestCycle().find(AjaxRequestTarget.class);
                if (target != null) {
                    target.add(InlineEditForm.this);
                }
            }
        };

        TextField<T> titleField = new TextField<T>("inputField", formModel) {
            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(FieldType.TEXT_FIELD.equals(fieldType));
            }
        };
        titleField.add(new AttributeAppender("placeholder", labelModel));
        titleField.setLabel(labelModel);

        TextArea<T> contentField = new TextArea<T>("textArea", formModel) {
            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(FieldType.TEXT_AREA.equals(fieldType));
            }
        };
        contentField.add(new AttributeAppender("placeholder", labelModel));
        contentField.setLabel(labelModel);

        AjaxSubmitLink submitLink = new AjaxSubmitLink("submit", form) {
            @Override
            protected void onAfterSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onAfterSubmit(target, form);
                InlineEditForm.this.onAfterSubmit(target, form);
            }
        };

        form.add(contentField);
        form.add(titleField);
        form.add(submitLink);
        add(form);
    }

    protected void onAfterSubmit(AjaxRequestTarget target, Form<?> form) {
    }
}
