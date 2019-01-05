package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CorePoint.
 */
@Entity
@Table(name = "core_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CorePoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "core_point_id")
    private Integer corePointId;

    @Column(name = "point_name")
    private String pointName;

    @Column(name = "accuracy")
    private String accuracy;

    @Column(name = "unit")
    private String unit;

    @Column(name = "jhi_max")
    private String max;

    @Column(name = "jhi_min")
    private String min;

    @Column(name = "core_source_id")
    private Integer coreSourceId;

    @Column(name = "core_data_type_id")
    private Integer coreDataTypeId;

    @Column(name = "event_severity")
    private Integer eventSeverity;

    @Column(name = "state_rule_id")
    private Integer stateRuleId;

    @Column(name = "readable")
    private Boolean readable;

    @Column(name = "writable")
    private Boolean writable;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "step")
    private Float step;

    @Column(name = "masked")
    private Boolean masked;

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

    public CorePoint corePointId(Integer corePointId) {
        this.corePointId = corePointId;
        return this;
    }

    public void setCorePointId(Integer corePointId) {
        this.corePointId = corePointId;
    }

    public String getPointName() {
        return pointName;
    }

    public CorePoint pointName(String pointName) {
        this.pointName = pointName;
        return this;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public CorePoint accuracy(String accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getUnit() {
        return unit;
    }

    public CorePoint unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMax() {
        return max;
    }

    public CorePoint max(String max) {
        this.max = max;
        return this;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public CorePoint min(String min) {
        this.min = min;
        return this;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public Integer getCoreSourceId() {
        return coreSourceId;
    }

    public CorePoint coreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
        return this;
    }

    public void setCoreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
    }

    public Integer getCoreDataTypeId() {
        return coreDataTypeId;
    }

    public CorePoint coreDataTypeId(Integer coreDataTypeId) {
        this.coreDataTypeId = coreDataTypeId;
        return this;
    }

    public void setCoreDataTypeId(Integer coreDataTypeId) {
        this.coreDataTypeId = coreDataTypeId;
    }

    public Integer getEventSeverity() {
        return eventSeverity;
    }

    public CorePoint eventSeverity(Integer eventSeverity) {
        this.eventSeverity = eventSeverity;
        return this;
    }

    public void setEventSeverity(Integer eventSeverity) {
        this.eventSeverity = eventSeverity;
    }

    public Integer getStateRuleId() {
        return stateRuleId;
    }

    public CorePoint stateRuleId(Integer stateRuleId) {
        this.stateRuleId = stateRuleId;
        return this;
    }

    public void setStateRuleId(Integer stateRuleId) {
        this.stateRuleId = stateRuleId;
    }

    public Boolean isReadable() {
        return readable;
    }

    public CorePoint readable(Boolean readable) {
        this.readable = readable;
        return this;
    }

    public void setReadable(Boolean readable) {
        this.readable = readable;
    }

    public Boolean isWritable() {
        return writable;
    }

    public CorePoint writable(Boolean writable) {
        this.writable = writable;
        return this;
    }

    public void setWritable(Boolean writable) {
        this.writable = writable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public CorePoint defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Float getStep() {
        return step;
    }

    public CorePoint step(Float step) {
        this.step = step;
        return this;
    }

    public void setStep(Float step) {
        this.step = step;
    }

    public Boolean isMasked() {
        return masked;
    }

    public CorePoint masked(Boolean masked) {
        this.masked = masked;
        return this;
    }

    public void setMasked(Boolean masked) {
        this.masked = masked;
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
        CorePoint corePoint = (CorePoint) o;
        if (corePoint.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), corePoint.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CorePoint{" +
            "id=" + getId() +
            ", corePointId=" + getCorePointId() +
            ", pointName='" + getPointName() + "'" +
            ", accuracy='" + getAccuracy() + "'" +
            ", unit='" + getUnit() + "'" +
            ", max='" + getMax() + "'" +
            ", min='" + getMin() + "'" +
            ", coreSourceId=" + getCoreSourceId() +
            ", coreDataTypeId=" + getCoreDataTypeId() +
            ", eventSeverity=" + getEventSeverity() +
            ", stateRuleId=" + getStateRuleId() +
            ", readable='" + isReadable() + "'" +
            ", writable='" + isWritable() + "'" +
            ", defaultValue='" + getDefaultValue() + "'" +
            ", step=" + getStep() +
            ", masked='" + isMasked() + "'" +
            "}";
    }
}
