package com.vaibhav.parkingReservation.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The created by user. */
    @Column(name = "added_by", nullable = false, insertable = true, updatable = false)
    @CreatedBy
    private String createdByUser;

    /** The creation time. */
    @Column(name = "added_date", nullable = false, insertable = true, updatable = false)
    @CreatedDate
    private Instant creationTime;

    /** The modified by user. */
    @Column(name = "last_modified_by", nullable = false, insertable = true, updatable = true)
    @LastModifiedBy
    private String modifiedByUser;

    @Column(name = "last_modified", nullable = false, insertable = true, updatable = true)
    @LastModifiedDate
    private Instant modificationTime;

    @Column(name = "is_deleted", nullable = false, insertable = true, updatable = true)
    private boolean isDeleted;

    public BaseEntity() {
        super();
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public void setModificationTime(Instant modificationTime) {
        this.modificationTime = modificationTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public Instant getCreationTime() {
        return this.creationTime;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(final String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public Instant getModificationTime() {
        return this.modificationTime;
    }
    @Column(name = "isDeleted", nullable = false)
    public boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(final boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
