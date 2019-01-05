package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A PointMask.
 */
@Entity
@Table(name = "point_mask")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PointMask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "jhi_comment")
    private String comment;

    @Column(name = "core_point_id")
    private Integer corePointId;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "operation_time")
    private Instant operationTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public PointMask userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public PointMask userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public PointMask comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCorePointId() {
        return corePointId;
    }

    public PointMask corePointId(Integer corePointId) {
        this.corePointId = corePointId;
        return this;
    }

    public void setCorePointId(Integer corePointId) {
        this.corePointId = corePointId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public PointMask deviceId(Integer deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Instant getOperationTime() {
        return operationTime;
    }

    public PointMask operationTime(Instant operationTime) {
        this.operationTime = operationTime;
        return this;
    }

    public void setOperationTime(Instant operationTime) {
        this.operationTime = operationTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PointMask pointMask = (PointMask) o;
        if (pointMask.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pointMask.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PointMask{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", userName='" + getUserName() + "'" +
            ", comment='" + getComment() + "'" +
            ", corePointId=" + getCorePointId() +
            ", deviceId=" + getDeviceId() +
            ", operationTime='" + getOperationTime() + "'" +
            "}";
    }
}
