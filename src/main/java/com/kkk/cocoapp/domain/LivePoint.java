package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A LivePoint.
 */
@Entity
@Table(name = "live_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LivePoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "core_point_id")
    private Integer corePointId;

    @Column(name = "core_point_name")
    private String corePointName;

    @Column(name = "core_source_id")
    private Integer coreSourceId;

    @Column(name = "core_source_name")
    private String coreSourceName;

    @Column(name = "birth_time")
    private Instant birthTime;

    @Column(name = "collect_value")
    private String collectValue;

    @Column(name = "state")
    private Integer state;

    @Column(name = "severity")
    private Integer severity;

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

    public LivePoint corePointId(Integer corePointId) {
        this.corePointId = corePointId;
        return this;
    }

    public void setCorePointId(Integer corePointId) {
        this.corePointId = corePointId;
    }

    public String getCorePointName() {
        return corePointName;
    }

    public LivePoint corePointName(String corePointName) {
        this.corePointName = corePointName;
        return this;
    }

    public void setCorePointName(String corePointName) {
        this.corePointName = corePointName;
    }

    public Integer getCoreSourceId() {
        return coreSourceId;
    }

    public LivePoint coreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
        return this;
    }

    public void setCoreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
    }

    public String getCoreSourceName() {
        return coreSourceName;
    }

    public LivePoint coreSourceName(String coreSourceName) {
        this.coreSourceName = coreSourceName;
        return this;
    }

    public void setCoreSourceName(String coreSourceName) {
        this.coreSourceName = coreSourceName;
    }

    public Instant getBirthTime() {
        return birthTime;
    }

    public LivePoint birthTime(Instant birthTime) {
        this.birthTime = birthTime;
        return this;
    }

    public void setBirthTime(Instant birthTime) {
        this.birthTime = birthTime;
    }

    public String getCollectValue() {
        return collectValue;
    }

    public LivePoint collectValue(String collectValue) {
        this.collectValue = collectValue;
        return this;
    }

    public void setCollectValue(String collectValue) {
        this.collectValue = collectValue;
    }

    public Integer getState() {
        return state;
    }

    public LivePoint state(Integer state) {
        this.state = state;
        return this;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSeverity() {
        return severity;
    }

    public LivePoint severity(Integer severity) {
        this.severity = severity;
        return this;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
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
        LivePoint livePoint = (LivePoint) o;
        if (livePoint.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), livePoint.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LivePoint{" +
            "id=" + getId() +
            ", corePointId=" + getCorePointId() +
            ", corePointName='" + getCorePointName() + "'" +
            ", coreSourceId=" + getCoreSourceId() +
            ", coreSourceName='" + getCoreSourceName() + "'" +
            ", birthTime='" + getBirthTime() + "'" +
            ", collectValue='" + getCollectValue() + "'" +
            ", state=" + getState() +
            ", severity=" + getSeverity() +
            "}";
    }
}
