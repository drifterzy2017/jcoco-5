package com.kkk.cocoapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A Device.
 */
@Entity
@Table(name = "device")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "description")
    private String description;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "device_category")
    private Integer deviceCategory;

    @Column(name = "status")
    private Integer status;

    @Column(name = "masked")
    private Boolean masked;

    //kkk add mannually
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "DeviceCoreSourceMap", joinColumns = @JoinColumn(name = "DeviceId"), inverseJoinColumns = @JoinColumn(name = "CoreSourceId"))
    private Set<CoreSource> coreSources;
    public Set<CoreSource> getCoreSources(){
        return  coreSources;
    }


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public Device deviceId(Integer deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public Device description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Device deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Device roomId(Integer roomId) {
        this.roomId = roomId;
        return this;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getDeviceCategory() {
        return deviceCategory;
    }

    public Device deviceCategory(Integer deviceCategory) {
        this.deviceCategory = deviceCategory;
        return this;
    }

    public void setDeviceCategory(Integer deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public Integer getStatus() {
        return status;
    }

    public Device status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean isMasked() {
        return masked;
    }

    public Device masked(Boolean masked) {
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
        Device device = (Device) o;
        if (device.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), device.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Device{" +
            "id=" + getId() +
            ", deviceId=" + getDeviceId() +
            ", description='" + getDescription() + "'" +
            ", deviceName='" + getDeviceName() + "'" +
            ", roomId=" + getRoomId() +
            ", deviceCategory=" + getDeviceCategory() +
            ", status=" + getStatus() +
            ", masked='" + isMasked() + "'" +
            "}";
    }
}
