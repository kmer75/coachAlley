package com.example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated;
    @Column(name = "deleted")
    private boolean deleted;


    public AbstractEntity() {

    }

    @PrePersist
    protected void onPrePersist() {
        setCreated(new Date());
        setUpdated(new Date());
        setDeleted(false);
    }

    @PreUpdate
    protected void onPreUpdate() {
        setUpdated(new Date());
    }

    @Override
    public int hashCode() {
        int hash = 10;
        return hash *= (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {

        String class1 = this.getClass().getName();
        String class2 = obj.getClass().getName();

        if (!class2.equals(class1)) {
            return false;
        }

        AbstractEntity other = (AbstractEntity) obj;

        return this.id == other.id;
    }





    @Override
    public String toString() {
        return String.format("id=%s, created=%s, updated=%s, deleted=%s", id, created, updated,
                deleted);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     *            the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     *            the updated to set
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     *            the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
