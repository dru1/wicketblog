package at.dru.wicketblog.components;

import org.apache.wicket.markup.html.panel.Panel;

public class ListPanel<T> extends Panel {

    private final Class<T> listType;

    public ListPanel(String id, Class<T> listType) {
        super(id);
        this.listType = listType;
    }
}
