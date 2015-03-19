package at.dru.wicketblog.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class DefaultEntity {

    private Long id;

    private Date created;

    private Date modified;

    private boolean disabled;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(@Nonnull Long id) {
        this.id = id;
    }

    @Nullable
    @Basic(optional = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(@Nonnull Date created) {
        this.created = created;
    }

    @Nullable
    @Basic(optional = true)
    public Date getModified() {
        return modified;
    }

    public void setModified(@Nonnull Date modified) {
        this.modified = modified;
    }

    @Nonnull
    @Basic(optional = false)
    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(@Nonnull Boolean disabled) {
        this.disabled = disabled;
    }

    @PrePersist
    public void prePersist() {
        if (created == null) {
            created = new Date();
        }
        modified = new Date();
    }
}
