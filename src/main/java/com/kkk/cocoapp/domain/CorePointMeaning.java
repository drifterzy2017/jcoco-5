package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CorePointMeaning.
 */
@Entity
@Table(name = "core_point_meaning")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CorePointMeaning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "core_source_id")
    private Integer coreSourceId;

    @Column(name = "core_point_id")
    private Integer corePointId;

    @Column(name = "jhi_value")
    private String value;

    @Column(name = "meaning")
    private String meaning;

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

    public CorePointMeaning coreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
        return this;
    }

    public void setCoreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
    }

    public Integer getCorePointId() {
        return corePointId;
    }

    public CorePointMeaning corePointId(Integer corePointId) {
        this.corePointId = corePointId;
        return this;
    }

    public void setCorePointId(Integer corePointId) {
        this.corePointId = corePointId;
    }

    public String getValue() {
        return value;
    }

    public CorePointMeaning value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMeaning() {
        return meaning;
    }

    public CorePointMeaning meaning(String meaning) {
        this.meaning = meaning;
        return this;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
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
        CorePointMeaning corePointMeaning = (CorePointMeaning) o;
        if (corePointMeaning.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), corePointMeaning.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CorePointMeaning{" +
            "id=" + getId() +
            ", coreSourceId=" + getCoreSourceId() +
            ", corePointId=" + getCorePointId() +
            ", value='" + getValue() + "'" +
            ", meaning='" + getMeaning() + "'" +
            "}";
    }
}
