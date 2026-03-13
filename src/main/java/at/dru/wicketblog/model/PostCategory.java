package at.dru.wicketblog.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;

@Entity
public class PostCategory extends AbstractEntity {

    private String name;

    private String contentClass;

    private String iconClass;

    private String backgroundClass;

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public String getContentClass() {
        return contentClass;
    }

    public void setContentClass(String contentClass) {
        this.contentClass = contentClass;
    }

    @Basic
    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    @Basic
    public String getBackgroundClass() {
        return backgroundClass;
    }

    public void setBackgroundClass(String backgroundClass) {
        this.backgroundClass = backgroundClass;
    }
}
