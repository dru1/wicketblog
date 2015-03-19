package at.dru.wicketblog.components;


import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class FormPanel<T extends DefaultEntity> extends Panel {

    @SpringBean
    protected EntityServiceRegistry entityServiceRegistry;

    protected final Class<T> entityClass;

    protected final IModel<T> formModel;

    protected EntityForm<T> entityForm;

    public FormPanel(String id, IModel<T> model, Class<T> entityClass) {
        super(id, model);
        this.formModel = model;
        this.entityClass = entityClass;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        entityForm = new EntityForm<>("form", formModel, entityClass);
        add(entityForm);
    }
}
