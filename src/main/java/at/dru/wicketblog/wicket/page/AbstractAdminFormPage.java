package at.dru.wicketblog.wicket.page;

import at.dru.wicketblog.model.DefaultEntity;
import at.dru.wicketblog.wicket.component.EntityFormPanel;
import at.dru.wicketblog.wicket.component.ListPanel;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractAdminFormPage<T extends DefaultEntity> extends AbstractAuthenticatedPage {

    private static final long serialVersionUID = 1L;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        IModel<T> formModel = new LoadableDetachableModel<T>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected T load() {
                return newInstance();
            }
        };

        add(getFormPanel("form", formModel));
        add(getListPanel("list"));
    }

    protected T newInstance() {
        Constructor<T> constructor;
        try {
            constructor = ReflectionUtils.accessibleConstructor(getFormType(), new Class<?>[] {});
            return constructor.newInstance(new Object[] {});
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new RuntimeException("Cannot create instance of type " + getFormType().getCanonicalName(), e);
        }
    }

    protected abstract ListPanel<T> getListPanel(@Nonnull String wicketId);

    protected abstract EntityFormPanel<T> getFormPanel(@Nonnull String wicketId, @Nonnull IModel<T> formModel);

    protected abstract Class<T> getFormType();

    @Nullable
    @Override
    protected String getRequiresRole() {
        return Roles.ADMIN;
    }

}
