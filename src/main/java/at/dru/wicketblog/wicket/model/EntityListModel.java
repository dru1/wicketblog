package at.dru.wicketblog.wicket.model;

import at.dru.wicketblog.model.AbstractEntity;
import at.dru.wicketblog.service.EntityServiceRegistry;
import com.google.common.collect.ImmutableList;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityListModel<E extends AbstractEntity> extends LoadableDetachableModel<List<E>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private EntityServiceRegistry entityServiceRegistry;

    private final Class<E> entityClass;

    public EntityListModel(@Nonnull Class<E> entityClass) {
        Injector.get().inject(this);

        this.entityClass = entityClass;
    }

    @Override
    protected List<E> load() {
        return ImmutableList.copyOf(entityServiceRegistry.forClass(entityClass).findAll());
    }

}
