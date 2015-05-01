package at.dru.wicketblog.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;

import java.io.Serializable;
import java.util.List;

public class AbstractFormBuilder<E> implements Serializable {

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
