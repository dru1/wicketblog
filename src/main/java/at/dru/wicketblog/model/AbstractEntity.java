package at.dru.wicketblog.model;

import at.dru.wicketblog.service.EntityPropertyDesc;
import at.dru.wicketblog.service.EntityPropertyType;
import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity {

    private Long id;

    private Date created;

    private Date modified;

    private boolean disabled;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EntityPropertyDesc(type = EntityPropertyType.ID, optional = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @EntityPropertyDesc(type = EntityPropertyType.DATETIME, optional = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Basic(optional = true)
    @EntityPropertyDesc(type = EntityPropertyType.DATETIME, optional = false)
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Basic(optional = false)
    @EntityPropertyDesc(type = EntityPropertyType.FLAG, optional = false)
    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
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
