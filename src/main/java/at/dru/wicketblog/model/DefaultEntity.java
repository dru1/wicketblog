package at.dru.wicketblog.model;

import java.util.Date;

import javax.annotation.Nonnull;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import at.dru.wicketblog.service.EntityPropertyDesc;
import at.dru.wicketblog.service.EntityPropertyType;

@MappedSuperclass
public abstract class DefaultEntity {

    private Long id;

    private Date created;

    private Date modified;

    private boolean disabled;

    @Nonnull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EntityPropertyDesc(type = EntityPropertyType.ID, optional = false)
    public Long getId() {
        return id;
    }

    public void setId(@Nonnull Long id) {
        this.id = id;
    }

    @Nonnull
    @Basic(optional = false)
    @EntityPropertyDesc(type = EntityPropertyType.DATETIME, optional = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(@Nonnull Date created) {
        this.created = created;
    }

    @Nonnull
    @Basic(optional = true)
    @EntityPropertyDesc(type = EntityPropertyType.DATETIME, optional = false)
    public Date getModified() {
        return modified;
    }

    public void setModified(@Nonnull Date modified) {
        this.modified = modified;
    }

    @Nonnull
    @Basic(optional = false)
    @EntityPropertyDesc(type = EntityPropertyType.FLAG, optional = false)
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
