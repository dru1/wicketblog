package at.dru.wicketblog.pages;

import at.dru.wicketblog.components.FormPanel;
import at.dru.wicketblog.components.ListPanel;
import at.dru.wicketblog.model.DefaultEntity;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractAdminFormPage<T extends DefaultEntity> extends AbstractAuthenticatedPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        IModel<T> formModel = new LoadableDetachableModel<T>() {
            @Override
            protected T load() {
                T entity = null;
                try {
                    entity = newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    error(e.getMessage());
                }
                return entity;
            }
        };

        add(getFormPanel("form", formModel));
        add(getListPanel("list"));
    }

    protected T newInstance() throws IllegalAccessException, InstantiationException {
        return getFormType().newInstance();
    }

    protected abstract ListPanel<T> getListPanel(@Nonnull String wicketId);

    protected abstract FormPanel<T> getFormPanel(@Nonnull String wicketId, @Nonnull IModel<T> formModel);

    protected abstract Class<T> getFormType();

    @Nullable
    @Override
    protected String getRequiresRole() {
        return Roles.ADMIN;
    }

}
