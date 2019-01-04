package com.kkk.cocoapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A LiveEvent.
 */
@Entity
@Table(name = "live_event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LiveEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "live_event_id")
    private Long liveEventId;

    @Column(name = "birth_time")
    private Instant birthTime;

    @Column(name = "cleared_by_id")
    private Integer clearedById;

    @Column(name = "cleared_by_name")
    private String clearedByName;

    @Column(name = "clear_time")
    private Instant clearTime;

    @Column(name = "jhi_comment")
    private String comment;

    @Column(name = "confirmer_id")
    private Integer confirmerId;

    @Column(name = "confirmer_name")
    private String confirmerName;

    @Column(name = "confirm_time")
    private Instant confirmTime;

    @Column(name = "core_point_id")
    private Integer corePointId;

    @Column(name = "core_point_name")
    private String corePointName;

    @Column(name = "core_source_id")
    private Integer coreSourceId;

    @Column(name = "core_source_name")
    private String coreSourceName;

    @Column(name = "occur_remark")
    private String occurRemark;

    @Column(name = "occur_value")
    private String occurValue;

    @Column(name = "severity_id")
    private Integer severityId;

    @Column(name = "severity_name")
    private String severityName;

    @Column(name = "state_id")
    private Integer stateId;

    @Column(name = "state_name")
    private String stateName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLiveEventId() {
        return liveEventId;
    }

    public LiveEvent liveEventId(Long liveEventId) {
        this.liveEventId = liveEventId;
        return this;
    }

    public void setLiveEventId(Long liveEventId) {
        this.liveEventId = liveEventId;
    }

    public Instant getBirthTime() {
        return birthTime;
    }

    public LiveEvent birthTime(Instant birthTime) {
        this.birthTime = birthTime;
        return this;
    }

    public void setBirthTime(Instant birthTime) {
        this.birthTime = birthTime;
    }

    public Integer getClearedById() {
        return clearedById;
    }

    public LiveEvent clearedById(Integer clearedById) {
        this.clearedById = clearedById;
        return this;
    }

    public void setClearedById(Integer clearedById) {
        this.clearedById = clearedById;
    }

    public String getClearedByName() {
        return clearedByName;
    }

    public LiveEvent clearedByName(String clearedByName) {
        this.clearedByName = clearedByName;
        return this;
    }

    public void setClearedByName(String clearedByName) {
        this.clearedByName = clearedByName;
    }

    public Instant getClearTime() {
        return clearTime;
    }

    public LiveEvent clearTime(Instant clearTime) {
        this.clearTime = clearTime;
        return this;
    }

    public void setClearTime(Instant clearTime) {
        this.clearTime = clearTime;
    }

    public String getComment() {
        return comment;
    }

    public LiveEvent comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getConfirmerId() {
        return confirmerId;
    }

    public LiveEvent confirmerId(Integer confirmerId) {
        this.confirmerId = confirmerId;
        return this;
    }

    public void setConfirmerId(Integer confirmerId) {
        this.confirmerId = confirmerId;
    }

    public String getConfirmerName() {
        return confirmerName;
    }

    public LiveEvent confirmerName(String confirmerName) {
        this.confirmerName = confirmerName;
        return this;
    }

    public void setConfirmerName(String confirmerName) {
        this.confirmerName = confirmerName;
    }

    public Instant getConfirmTime() {
        return confirmTime;
    }

    public LiveEvent confirmTime(Instant confirmTime) {
        this.confirmTime = confirmTime;
        return this;
    }

    public void setConfirmTime(Instant confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getCorePointId() {
        return corePointId;
    }

    public LiveEvent corePointId(Integer corePointId) {
        this.corePointId = corePointId;
        return this;
    }

    public void setCorePointId(Integer corePointId) {
        this.corePointId = corePointId;
    }

    public String getCorePointName() {
        return corePointName;
    }

    public LiveEvent corePointName(String corePointName) {
        this.corePointName = corePointName;
        return this;
    }

    public void setCorePointName(String corePointName) {
        this.corePointName = corePointName;
    }

    public Integer getCoreSourceId() {
        return coreSourceId;
    }

    public LiveEvent coreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
        return this;
    }

    public void setCoreSourceId(Integer coreSourceId) {
        this.coreSourceId = coreSourceId;
    }

    public String getCoreSourceName() {
        return coreSourceName;
    }

    public LiveEvent coreSourceName(String coreSourceName) {
        this.coreSourceName = coreSourceName;
        return this;
    }

    public void setCoreSourceName(String coreSourceName) {
        this.coreSourceName = coreSourceName;
    }

    public String getOccurRemark() {
        return occurRemark;
    }

    public LiveEvent occurRemark(String occurRemark) {
        this.occurRemark = occurRemark;
        return this;
    }

    public void setOccurRemark(String occurRemark) {
        this.occurRemark = occurRemark;
    }

    public String getOccurValue() {
        return occurValue;
    }

    public LiveEvent occurValue(String occurValue) {
        this.occurValue = occurValue;
        return this;
    }

    public void setOccurValue(String occurValue) {
        this.occurValue = occurValue;
    }

    public Integer getSeverityId() {
        return severityId;
    }

    public LiveEvent severityId(Integer severityId) {
        this.severityId = severityId;
        return this;
    }

    public void setSeverityId(Integer severityId) {
        this.severityId = severityId;
    }

    public String getSeverityName() {
        return severityName;
    }

    public LiveEvent severityName(String severityName) {
        this.severityName = severityName;
        return this;
    }

    public void setSeverityName(String severityName) {
        this.severityName = severityName;
    }

    public Integer getStateId() {
        return stateId;
    }

    public LiveEvent stateId(Integer stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public LiveEvent stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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
        LiveEvent liveEvent = (LiveEvent) o;
        if (liveEvent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), liveEvent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LiveEvent{" +
            "id=" + getId() +
            ", liveEventId=" + getLiveEventId() +
            ", birthTime='" + getBirthTime() + "'" +
            ", clearedById=" + getClearedById() +
            ", clearedByName='" + getClearedByName() + "'" +
            ", clearTime='" + getClearTime() + "'" +
            ", comment='" + getComment() + "'" +
            ", confirmerId=" + getConfirmerId() +
            ", confirmerName='" + getConfirmerName() + "'" +
            ", confirmTime='" + getConfirmTime() + "'" +
            ", corePointId=" + getCorePointId() +
            ", corePointName='" + getCorePointName() + "'" +
            ", coreSourceId=" + getCoreSourceId() +
            ", coreSourceName='" + getCoreSourceName() + "'" +
            ", occurRemark='" + getOccurRemark() + "'" +
            ", occurValue='" + getOccurValue() + "'" +
            ", severityId=" + getSeverityId() +
            ", severityName='" + getSeverityName() + "'" +
            ", stateId=" + getStateId() +
            ", stateName='" + getStateName() + "'" +
            "}";
    }
}
