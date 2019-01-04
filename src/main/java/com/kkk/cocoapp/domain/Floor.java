package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Floor.
 */
@Entity
@Table(name = "floor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Floor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "floor_id")
    private Integer floorId;

    @Column(name = "building_id")
    private Integer buildingId;

    @Column(name = "description")
    private String description;

    @Column(name = "floor_name")
    private String floorName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public Floor floorId(Integer floorId) {
        this.floorId = floorId;
        return this;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public Floor buildingId(Integer buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getDescription() {
        return description;
    }

    public Floor description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFloorName() {
        return floorName;
    }

    public Floor floorName(String floorName) {
        this.floorName = floorName;
        return this;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
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
        Floor floor = (Floor) o;
        if (floor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), floor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Floor{" +
            "id=" + getId() +
            ", floorId=" + getFloorId() +
            ", buildingId=" + getBuildingId() +
            ", description='" + getDescription() + "'" +
            ", floorName='" + getFloorName() + "'" +
            "}";
    }
}
