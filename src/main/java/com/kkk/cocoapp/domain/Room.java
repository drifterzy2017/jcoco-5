package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Room.
 */
@Entity
@Table(name = "room")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "description")
    private String description;

    @Column(name = "floor_id")
    private Integer floorId;

    @Column(name = "room_name")
    private String roomName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Room roomId(Integer roomId) {
        this.roomId = roomId;
        return this;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getDescription() {
        return description;
    }

    public Room description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public Room floorId(Integer floorId) {
        this.floorId = floorId;
        return this;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public String getRoomName() {
        return roomName;
    }

    public Room roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
        Room room = (Room) o;
        if (room.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), room.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Room{" +
            "id=" + getId() +
            ", roomId=" + getRoomId() +
            ", description='" + getDescription() + "'" +
            ", floorId=" + getFloorId() +
            ", roomName='" + getRoomName() + "'" +
            "}";
    }
}
