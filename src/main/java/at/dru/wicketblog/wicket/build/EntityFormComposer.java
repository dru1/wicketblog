package at.dru.wicketblog.wicket.build;

import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.wicket.component.EntityForm;
import at.dru.wicketblog.wicket.component.FieldType;
import at.dru.wicketblog.wicket.component.FormRow;
import at.dru.wicketblog.wicket.model.MetaModel;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import javax.annotation.Nonnull;
import javax.persistence.metamodel.Attribute;
import java.util.List;

public final class EntityFormComposer<E extends DefaultEntity> {

    private final EntityForm<E> entityForm;
    private final FormComposer<E> delegate;

    public EntityFormComposer(@Nonnull EntityForm<E> entityForm) {
        this.entityForm = entityForm;
        this.delegate = new FormComposer<>(entityForm);
    }

    @Nonnull
    public EntityFormComposer<E> textField(@Nonnull Attribute<E, ?> jpaAttribute, @Nonnull FieldType fieldType) {
        return textField(jpaAttribute.getName(), fieldType);
    }

    @Nonnull
    public EntityFormComposer<E> textField(@Nonnull String fieldName, @Nonnull FieldType fieldType) {
        delegate.textField(fieldName, fieldType, new MetaModel<>(entityForm.getEntityClass(), fieldName));
        return this;
    }

    @Nonnull
    public <C extends DefaultEntity> EntityFormComposer<E> entityChoice(@Nonnull Attribute<E, ?> jpaAttribute, @Nonnull IModel<List<C>> choicesModel) {
        return entityChoice(jpaAttribute.getName(), choicesModel);
    }

    @Nonnull
    public <C extends DefaultEntity> EntityFormComposer<E> entityChoice(@Nonnull String fieldName, @Nonnull IModel<List<C>> choicesModel) {
        PropertyModel<C> fieldModel = new PropertyModel<>(entityForm.getModel(), fieldName);
        MetaModel<E> labelModel = new MetaModel<>(entityForm.getEntityClass(), fieldName);
        FormRow formRow = new FormRow(fieldName, labelModel);
        formRow.add(new DropDownChoice<>(fieldName, fieldModel, choicesModel).setLabel(labelModel));
        delegate.formRow(formRow);
        return this;
    }

}
