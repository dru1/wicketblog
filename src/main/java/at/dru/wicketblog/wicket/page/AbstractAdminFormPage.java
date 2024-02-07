package at.dru.wicketblog.wicket.page;

import at.dru.wicketblog.model.AbstractEntity;
import at.dru.wicketblog.wicket.component.EntityFormPanel;
import at.dru.wicketblog.wicket.component.ListPanel;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.springframework.beans.BeanUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractAdminFormPage<T extends AbstractEntity> extends AbstractAuthenticatedPage {

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
        return BeanUtils.instantiateClass(getFormType());
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
