package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Cov.
 */
@Entity
@Table(name = "cov")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cov implements Serializable {

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

    @Column(name = "qos")
    private Integer qos;

    @Column(name = "birth_time")
    private Instant birthTime;

    @Column(name = "jhi_value")
    private String value;

    @Column(name = "state")
    private Integer state;

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

    public Cov corePointId(Integer corePointId) {
        this.corePointId = corePointId;
        return this;
    }

    public void setCorePointId(Integer corePointId) {
        this.corePointId = corePointId;
    }

    public Integer getCoreSourceId() {
        return coreSourceId;
    }

    public Cov coreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
        return this;
    }

    public void setCoreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
    }

    public Integer getEngineId() {
        return engineId;
    }

    public Cov engineId(Integer engineId) {
        this.engineId = engineId;
        return this;
    }

    public void setEngineId(Integer engineId) {
        this.engineId = engineId;
    }

    public Integer getQos() {
        return qos;
    }

    public Cov qos(Integer qos) {
        this.qos = qos;
        return this;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public Instant getBirthTime() {
        return birthTime;
    }

    public Cov birthTime(Instant birthTime) {
        this.birthTime = birthTime;
        return this;
    }

    public void setBirthTime(Instant birthTime) {
        this.birthTime = birthTime;
    }

    public String getValue() {
        return value;
    }

    public Cov value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getState() {
        return state;
    }

    public Cov state(Integer state) {
        this.state = state;
        return this;
    }

    public void setState(Integer state) {
        this.state = state;
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
        Cov cov = (Cov) o;
        if (cov.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cov.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cov{" +
            "id=" + getId() +
            ", corePointId=" + getCorePointId() +
            ", coreSourceId=" + getCoreSourceId() +
            ", engineId=" + getEngineId() +
            ", qos=" + getQos() +
            ", birthTime='" + getBirthTime() + "'" +
            ", value='" + getValue() + "'" +
            ", state=" + getState() +
            "}";
    }
}
