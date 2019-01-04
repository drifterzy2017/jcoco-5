package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Building.
 */
@Entity
@Table(name = "building")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "building_id")
    private Integer buildingId;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "description")
    private String description;

    @Column(name = "park_id")
    private Integer parkId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public Building buildingId(Integer buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public Building buildingName(String buildingName) {
        this.buildingName = buildingName;
        return this;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getDescription() {
        return description;
    }

    public Building description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParkId() {
        return parkId;
    }

    public Building parkId(Integer parkId) {
        this.parkId = parkId;
        return this;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
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
        Building building = (Building) o;
        if (building.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), building.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Building{" +
            "id=" + getId() +
            ", buildingId=" + getBuildingId() +
            ", buildingName='" + getBuildingName() + "'" +
            ", description='" + getDescription() + "'" +
            ", parkId=" + getParkId() +
            "}";
    }
}
