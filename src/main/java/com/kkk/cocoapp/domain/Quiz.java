package com.kkk.cocoapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Quiz.
 */
@Entity
@Table(name = "quiz")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "test_key")
    private String testKey;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "lib_name")
    private String libName;

    @Column(name = "is_auto_submit")
    private Boolean isAutoSubmit;

    @Column(name = "my_round")
    private Integer myRound;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "success_time")
    private Instant successTime;

    @Column(name = "topmost_seconds")
    private Integer topmostSeconds;

    @Column(name = "cost_seconds")
    private Integer costSeconds;

    @Column(name = "mark")
    private Float mark;

    @Column(name = "pass_count")
    private Integer passCount;

    @Column(name = "fail_count")
    private Integer failCount;

    @Column(name = "cent_count")
    private Integer centCount;

    @Column(name = "point")
    private Integer point;

    @Column(name = "used")
    private Boolean used;

    @Column(name = "use_time")
    private Instant useTime;

    @Column(name = "use_note")
    private String useNote;

    @OneToMany(mappedBy = "quiz")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Question> questions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestKey() {
        return testKey;
    }

    public Quiz testKey(String testKey) {
        this.testKey = testKey;
        return this;
    }

    public void setTestKey(String testKey) {
        this.testKey = testKey;
    }

    public String getTestName() {
        return testName;
    }

    public Quiz testName(String testName) {
        this.testName = testName;
        return this;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getLibName() {
        return libName;
    }

    public Quiz libName(String libName) {
        this.libName = libName;
        return this;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public Boolean isIsAutoSubmit() {
        return isAutoSubmit;
    }

    public Quiz isAutoSubmit(Boolean isAutoSubmit) {
        this.isAutoSubmit = isAutoSubmit;
        return this;
    }

    public void setIsAutoSubmit(Boolean isAutoSubmit) {
        this.isAutoSubmit = isAutoSubmit;
    }

    public Integer getMyRound() {
        return myRound;
    }

    public Quiz myRound(Integer myRound) {
        this.myRound = myRound;
        return this;
    }

    public void setMyRound(Integer myRound) {
        this.myRound = myRound;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Quiz startTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getSuccessTime() {
        return successTime;
    }

    public Quiz successTime(Instant successTime) {
        this.successTime = successTime;
        return this;
    }

    public void setSuccessTime(Instant successTime) {
        this.successTime = successTime;
    }

    public Integer getTopmostSeconds() {
        return topmostSeconds;
    }

    public Quiz topmostSeconds(Integer topmostSeconds) {
        this.topmostSeconds = topmostSeconds;
        return this;
    }

    public void setTopmostSeconds(Integer topmostSeconds) {
        this.topmostSeconds = topmostSeconds;
    }

    public Integer getCostSeconds() {
        return costSeconds;
    }

    public Quiz costSeconds(Integer costSeconds) {
        this.costSeconds = costSeconds;
        return this;
    }

    public void setCostSeconds(Integer costSeconds) {
        this.costSeconds = costSeconds;
    }

    public Float getMark() {
        return mark;
    }

    public Quiz mark(Float mark) {
        this.mark = mark;
        return this;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public Integer getPassCount() {
        return passCount;
    }

    public Quiz passCount(Integer passCount) {
        this.passCount = passCount;
        return this;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public Quiz failCount(Integer failCount) {
        this.failCount = failCount;
        return this;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getCentCount() {
        return centCount;
    }

    public Quiz centCount(Integer centCount) {
        this.centCount = centCount;
        return this;
    }

    public void setCentCount(Integer centCount) {
        this.centCount = centCount;
    }

    public Integer getPoint() {
        return point;
    }

    public Quiz point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Boolean isUsed() {
        return used;
    }

    public Quiz used(Boolean used) {
        this.used = used;
        return this;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Instant getUseTime() {
        return useTime;
    }

    public Quiz useTime(Instant useTime) {
        this.useTime = useTime;
        return this;
    }

    public void setUseTime(Instant useTime) {
        this.useTime = useTime;
    }

    public String getUseNote() {
        return useNote;
    }

    public Quiz useNote(String useNote) {
        this.useNote = useNote;
        return this;
    }

    public void setUseNote(String useNote) {
        this.useNote = useNote;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Quiz questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Quiz addQuestion(Question question) {
        this.questions.add(question);
        question.setQuiz(this);
        return this;
    }

    public Quiz removeQuestion(Question question) {
        this.questions.remove(question);
        question.setQuiz(null);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
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
        Quiz quiz = (Quiz) o;
        if (quiz.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quiz.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Quiz{" +
            "id=" + getId() +
            ", testKey='" + getTestKey() + "'" +
            ", testName='" + getTestName() + "'" +
            ", libName='" + getLibName() + "'" +
            ", isAutoSubmit='" + isIsAutoSubmit() + "'" +
            ", myRound=" + getMyRound() +
            ", startTime='" + getStartTime() + "'" +
            ", successTime='" + getSuccessTime() + "'" +
            ", topmostSeconds=" + getTopmostSeconds() +
            ", costSeconds=" + getCostSeconds() +
            ", mark=" + getMark() +
            ", passCount=" + getPassCount() +
            ", failCount=" + getFailCount() +
            ", centCount=" + getCentCount() +
            ", point=" + getPoint() +
            ", used='" + isUsed() + "'" +
            ", useTime='" + getUseTime() + "'" +
            ", useNote='" + getUseNote() + "'" +
            "}";
    }
}
