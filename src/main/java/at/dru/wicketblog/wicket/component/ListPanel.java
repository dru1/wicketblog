package at.dru.wicketblog.wicket.component;

import org.apache.wicket.IGenericComponent;
import org.apache.wicket.markup.html.panel.Panel;

import javax.annotation.Nonnull;

public class ListPanel<T> extends Panel implements IGenericComponent<T, ListPanel<T>> {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private final Class<T> listType;

    public ListPanel(@Nonnull String id, @Nonnull Class<T> listType) {
        super(id);

        this.listType = listType;
    }

}
