package at.dru.wicketblog.wicket.build;

import at.dru.wicketblog.model.AbstractEntity;
import at.dru.wicketblog.wicket.component.EntityForm;
import at.dru.wicketblog.wicket.component.FieldType;
import at.dru.wicketblog.wicket.component.FormRow;
import at.dru.wicketblog.wicket.model.MetaModel;
import jakarta.persistence.metamodel.Attribute;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.List;

public final class EntityFormComposer<E extends AbstractEntity> {

    private final EntityForm<E> entityForm;
    private final FormComposer<E> delegate;

    public EntityFormComposer(EntityForm<E> entityForm) {
        this.entityForm = entityForm;
        this.delegate = new FormComposer<>(entityForm);
    }

    public EntityFormComposer<E> textField(Attribute<E, ?> jpaAttribute, FieldType fieldType) {
        return textField(jpaAttribute.getName(), fieldType);
    }

    public EntityFormComposer<E> textField(String fieldName, FieldType fieldType) {
        delegate.textField(fieldName, fieldType, new MetaModel<>(entityForm.getEntityClass(), fieldName));
        return this;
    }

    public <C extends AbstractEntity> EntityFormComposer<E> entityChoice(Attribute<E, ?> jpaAttribute, IModel<List<C>> choicesModel) {
        return entityChoice(jpaAttribute.getName(), choicesModel);
    }

    public <C extends AbstractEntity> EntityFormComposer<E> entityChoice(String fieldName, IModel<List<C>> choicesModel) {
        PropertyModel<C> fieldModel = new PropertyModel<>(entityForm.getModel(), fieldName);
        MetaModel<E> labelModel = new MetaModel<>(entityForm.getEntityClass(), fieldName);
        FormRow formRow = new FormRow(fieldName, labelModel);
        formRow.add(new DropDownChoice<>(fieldName, fieldModel, choicesModel).setLabel(labelModel));
        delegate.formRow(formRow);
        return this;
    }

}
