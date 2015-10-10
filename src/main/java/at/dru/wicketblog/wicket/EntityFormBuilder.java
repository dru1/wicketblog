package at.dru.wicketblog.wicket;

import at.dru.wicketblog.components.FieldType;
import at.dru.wicketblog.components.FormRow;
import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import com.google.common.collect.Lists;
import org.apache.wicket.Component;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public final class EntityFormBuilder<E extends DefaultEntity> extends AbstractFormBuilder<E> {

    private final Class<E> entityClass;

    private final IModel<E> entityModel;

    @SpringBean
    private EntityServiceRegistry entityServiceRegistry;

    private final FormBuilder<E> baseBuilder;

    public EntityFormBuilder(Class<E> entityClass, IModel<E> entityModel) {
        super(new ArrayList<Component>());

        this.entityClass = entityClass;
        this.entityModel = entityModel;
        this.baseBuilder = new FormBuilder<>(entityModel, componentList);
        Injector.get().inject(this);
    }

    public EntityFormBuilder<E> withTextField(String fieldName, FieldType fieldType) {
        baseBuilder.withTextField(fieldName, fieldType, new MetaModel<>(entityClass, fieldName));
        return this;
    }

    public <C extends DefaultEntity> EntityFormBuilder<E> withSingleChoice(String fieldName, final Class<C> choiceClass) {
        if (!DefaultEntity.class.isAssignableFrom(choiceClass)) {
            throw new IllegalArgumentException("Invalid type: " + choiceClass);
        }

        PropertyModel<C> fieldModel = new PropertyModel<>(entityModel, fieldName);
        final MetaModel<E> labelModel = new MetaModel<>(entityClass, fieldName);
        IModel<List<C>> choiceModel = new LoadableDetachableModel<List<C>>() {
            @Override
            protected List<C> load() {
                return Lists.newArrayList(entityServiceRegistry.forClass(choiceClass).findAll());
            }
        };

        FormRow formRow = new FormRow(fieldName) {
            @Override
            public IModel<String> getLabel() {
                return labelModel;
            }
        };

        formRow.getBodyContainer().add(new DropDownChoice<>(fieldName, fieldModel, choiceModel).setLabel(labelModel));
        componentList.add(formRow);

        return this;
    }


}
