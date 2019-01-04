package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DeviceState.
 */
@Entity
@Table(name = "device_state")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeviceState implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "state_id")
    private Integer stateId;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "remark")
    private String remark;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStateId() {
        return stateId;
    }

    public DeviceState stateId(Integer stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public DeviceState stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getRemark() {
        return remark;
    }

    public DeviceState remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        DeviceState deviceState = (DeviceState) o;
        if (deviceState.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deviceState.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeviceState{" +
            "id=" + getId() +
            ", stateId=" + getStateId() +
            ", stateName='" + getStateName() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
