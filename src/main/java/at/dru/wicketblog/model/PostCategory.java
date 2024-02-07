package at.dru.wicketblog.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;

import javax.annotation.Nonnull;

@Entity
public class PostCategory extends AbstractEntity {

    private String name;

    private String contentClass;

    private String iconClass;

    private String backgroundClass;

    @Nonnull
    @Basic
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    @Basic
    public String getContentClass() {
        return contentClass;
    }

    public void setContentClass(@Nonnull String contentClass) {
        this.contentClass = contentClass;
    }

    @Nonnull
    @Basic
    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(@Nonnull String iconClass) {
        this.iconClass = iconClass;
    }

    @Nonnull
    @Basic
    public String getBackgroundClass() {
        return backgroundClass;
    }

    public void setBackgroundClass(@Nonnull String backgroundClass) {
        this.backgroundClass = backgroundClass;
    }
}
