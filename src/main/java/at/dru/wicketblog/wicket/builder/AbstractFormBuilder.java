package at.dru.wicketblog.wicket.builder;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;

public class AbstractFormBuilder<E> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected List<Component> componentList;

    public AbstractFormBuilder(List<Component> componentList) {
        this.componentList = componentList;
    }

    public void build(Form<E> form) {
        for (Component component : componentList) {
            form.add(component);
        }
    }
}
