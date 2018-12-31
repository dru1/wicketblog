package at.dru.wicketblog.wicket.model;

import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.service.MetaModelService;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MetaModel<T extends DefaultEntity> implements IModel<String> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private MetaModelService metaModelService;

    private final Class<T> entityClass;

    private final String property;

    private transient String modelObject;

    private boolean attached = false;

    public MetaModel(Class<T> entityClass, String property) {
        this.entityClass = entityClass;
        this.property = property;
        Injector.get().inject(this);
    }

    @Override
    public String getObject() {
        if (!attached) {
            modelObject = metaModelService.getFieldName(entityClass, property);
            attached = true;
        }
        return modelObject;
    }

    @Override
    public void setObject(String object) {
        modelObject = object;
        attached = true;
    }

    @Override
    public void detach() {
        modelObject = null;
        attached = false;
    }
}
