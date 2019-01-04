package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Park.
 */
@Entity
@Table(name = "park")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Park implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "park_id")
    private Integer parkId;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "park_address")
    private String parkAddress;

    @Column(name = "park_name")
    private String parkName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParkId() {
        return parkId;
    }

    public Park parkId(Integer parkId) {
        this.parkId = parkId;
        return this;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getDescription() {
        return description;
    }

    public Park description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Park latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Park longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getParkAddress() {
        return parkAddress;
    }

    public Park parkAddress(String parkAddress) {
        this.parkAddress = parkAddress;
        return this;
    }

    public void setParkAddress(String parkAddress) {
        this.parkAddress = parkAddress;
    }

    public String getParkName() {
        return parkName;
    }

    public Park parkName(String parkName) {
        this.parkName = parkName;
        return this;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
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
        Park park = (Park) o;
        if (park.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), park.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Park{" +
            "id=" + getId() +
            ", parkId=" + getParkId() +
            ", description='" + getDescription() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", parkAddress='" + getParkAddress() + "'" +
            ", parkName='" + getParkName() + "'" +
            "}";
    }
}
