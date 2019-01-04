package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CoreSource.
 */
@Entity
@Table(name = "core_source")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CoreSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "core_source_id")
    private Integer coreSourceId;

    @Column(name = "source_name")
    private String sourceName;

    @Column(name = "engine_id")
    private Integer engineId;

    @Column(name = "mapper_id")
    private Integer mapperId;

    @Column(name = "link_state")
    private Integer linkState;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCoreSourceId() {
        return coreSourceId;
    }

    public CoreSource coreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
        return this;
    }

    public void setCoreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public CoreSource sourceName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getEngineId() {
        return engineId;
    }

    public CoreSource engineId(Integer engineId) {
        this.engineId = engineId;
        return this;
    }

    public void setEngineId(Integer engineId) {
        this.engineId = engineId;
    }

    public Integer getMapperId() {
        return mapperId;
    }

    public CoreSource mapperId(Integer mapperId) {
        this.mapperId = mapperId;
        return this;
    }

    public void setMapperId(Integer mapperId) {
        this.mapperId = mapperId;
    }

    public Integer getLinkState() {
        return linkState;
    }

    public CoreSource linkState(Integer linkState) {
        this.linkState = linkState;
        return this;
    }

    public void setLinkState(Integer linkState) {
        this.linkState = linkState;
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
        CoreSource coreSource = (CoreSource) o;
        if (coreSource.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coreSource.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoreSource{" +
            "id=" + getId() +
            ", coreSourceId=" + getCoreSourceId() +
            ", sourceName='" + getSourceName() + "'" +
            ", engineId=" + getEngineId() +
            ", mapperId=" + getMapperId() +
            ", linkState=" + getLinkState() +
            "}";
    }
}
