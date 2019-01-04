package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CoreEventSeverity.
 */
@Entity
@Table(name = "core_event_severity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CoreEventSeverity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "event_severity_id")
    private Integer eventSeverityId;

    @Column(name = "severity_name")
    private String severityName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEventSeverityId() {
        return eventSeverityId;
    }

    public CoreEventSeverity eventSeverityId(Integer eventSeverityId) {
        this.eventSeverityId = eventSeverityId;
        return this;
    }

    public void setEventSeverityId(Integer eventSeverityId) {
        this.eventSeverityId = eventSeverityId;
    }

    public String getSeverityName() {
        return severityName;
    }

    public CoreEventSeverity severityName(String severityName) {
        this.severityName = severityName;
        return this;
    }

    public void setSeverityName(String severityName) {
        this.severityName = severityName;
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
        CoreEventSeverity coreEventSeverity = (CoreEventSeverity) o;
        if (coreEventSeverity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coreEventSeverity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoreEventSeverity{" +
            "id=" + getId() +
            ", eventSeverityId=" + getEventSeverityId() +
            ", severityName='" + getSeverityName() + "'" +
            "}";
    }
}
