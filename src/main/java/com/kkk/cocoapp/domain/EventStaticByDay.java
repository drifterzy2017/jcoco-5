package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EventStaticByDay.
 */
@Entity
@Table(name = "event_static_by_day")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EventStaticByDay implements Serializable {
    public EventStaticByDay(){

    }
    public EventStaticByDay(int staticDay, int severity1,int severity2, int severity3, int severity4){
        this.staticDay = staticDay;
        this.severity1 =severity1;
        this.severity2 = severity2;
        this.severity3 = severity3;
        this.severity4 =severity4;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "static_day")
    private Integer staticDay;

    @Column(name = "severity_1")
    private Integer severity1;

    @Column(name = "severity_2")
    private Integer severity2;

    @Column(name = "severity_3")
    private Integer severity3;

    @Column(name = "severity_4")
    private Integer severity4;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStaticDay() {
        return staticDay;
    }

    public EventStaticByDay staticDay(Integer staticDay) {
        this.staticDay = staticDay;
        return this;
    }

    public void setStaticDay(Integer staticDay) {
        this.staticDay = staticDay;
    }

    public Integer getSeverity1() {
        return severity1;
    }

    public EventStaticByDay severity1(Integer severity1) {
        this.severity1 = severity1;
        return this;
    }

    public void setSeverity1(Integer severity1) {
        this.severity1 = severity1;
    }

    public Integer getSeverity2() {
        return severity2;
    }

    public EventStaticByDay severity2(Integer severity2) {
        this.severity2 = severity2;
        return this;
    }

    public void setSeverity2(Integer severity2) {
        this.severity2 = severity2;
    }

    public Integer getSeverity3() {
        return severity3;
    }

    public EventStaticByDay severity3(Integer severity3) {
        this.severity3 = severity3;
        return this;
    }

    public void setSeverity3(Integer severity3) {
        this.severity3 = severity3;
    }

    public Integer getSeverity4() {
        return severity4;
    }

    public EventStaticByDay severity4(Integer severity4) {
        this.severity4 = severity4;
        return this;
    }

    public void setSeverity4(Integer severity4) {
        this.severity4 = severity4;
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
        EventStaticByDay eventStaticByDay = (EventStaticByDay) o;
        if (eventStaticByDay.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventStaticByDay.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventStaticByDay{" +
            "id=" + getId() +
            ", staticDay=" + getStaticDay() +
            ", severity1=" + getSeverity1() +
            ", severity2=" + getSeverity2() +
            ", severity3=" + getSeverity3() +
            ", severity4=" + getSeverity4() +
            "}";
    }
}
