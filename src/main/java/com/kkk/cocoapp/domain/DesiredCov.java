package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DesiredCov.
 */
@Entity
@Table(name = "desired_cov")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DesiredCov implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "core_point_id")
    private Integer corePointId;

    @Column(name = "core_source_id")
    private Integer coreSourceId;

    @Column(name = "engine_id")
    private Integer engineId;

    @Column(name = "desired_value")
    private String desiredValue;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "birth_time")
    private Instant birthTime;

    @Column(name = "state")
    private Integer state;

    @Column(name = "message")
    private String message;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCorePointId() {
        return corePointId;
    }

    public DesiredCov corePointId(Integer corePointId) {
        this.corePointId = corePointId;
        return this;
    }

    public void setCorePointId(Integer corePointId) {
        this.corePointId = corePointId;
    }

    public Integer getCoreSourceId() {
        return coreSourceId;
    }

    public DesiredCov coreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
        return this;
    }

    public void setCoreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
    }

    public Integer getEngineId() {
        return engineId;
    }

    public DesiredCov engineId(Integer engineId) {
        this.engineId = engineId;
        return this;
    }

    public void setEngineId(Integer engineId) {
        this.engineId = engineId;
    }

    public String getDesiredValue() {
        return desiredValue;
    }

    public DesiredCov desiredValue(String desiredValue) {
        this.desiredValue = desiredValue;
        return this;
    }

    public void setDesiredValue(String desiredValue) {
        this.desiredValue = desiredValue;
    }

    public Integer getUserId() {
        return userId;
    }

    public DesiredCov userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Instant getBirthTime() {
        return birthTime;
    }

    public DesiredCov birthTime(Instant birthTime) {
        this.birthTime = birthTime;
        return this;
    }

    public void setBirthTime(Instant birthTime) {
        this.birthTime = birthTime;
    }

    public Integer getState() {
        return state;
    }

    public DesiredCov state(Integer state) {
        this.state = state;
        return this;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public DesiredCov message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
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
        DesiredCov desiredCov = (DesiredCov) o;
        if (desiredCov.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), desiredCov.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DesiredCov{" +
            "id=" + getId() +
            ", corePointId=" + getCorePointId() +
            ", coreSourceId=" + getCoreSourceId() +
            ", engineId=" + getEngineId() +
            ", desiredValue='" + getDesiredValue() + "'" +
            ", userId=" + getUserId() +
            ", birthTime='" + getBirthTime() + "'" +
            ", state=" + getState() +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
