package com.kkk.cocoapp.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LibQuestion.
 */
@Entity
@Table(name = "lib_question")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class LibQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "lib_name")
    private String libName;

    @Column(name = "ask")
    private String ask;

    @Column(name = "answer")
    private String answer;

    @Column(name = "count_pass")
    private Integer countPass;

    @Column(name = "count_fail")
    private Integer countFail;

    @Column(name = "count_rate")
    private Integer countRate;

    @Column(name = "count_pass_again")
    private Integer countPassAgain;

    @Column(name = "is_pass")
    private Boolean isPass;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibName() {
        return libName;
    }

    public LibQuestion libName(String libName) {
        this.libName = libName;
        return this;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getAsk() {
        return ask;
    }

    public LibQuestion ask(String ask) {
        this.ask = ask;
        return this;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAnswer() {
        return answer;
    }

    public LibQuestion answer(String answer) {
        this.answer = answer;
        return this;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCountPass() {
        return countPass;
    }

    public LibQuestion countPass(Integer countPass) {
        this.countPass = countPass;
        return this;
    }

    public void setCountPass(Integer countPass) {
        this.countPass = countPass;
    }

    public Integer getCountFail() {
        return countFail;
    }

    public LibQuestion countFail(Integer countFail) {
        this.countFail = countFail;
        return this;
    }

    public void setCountFail(Integer countFail) {
        this.countFail = countFail;
    }

    public Integer getCountRate() {
        return countRate;
    }

    public LibQuestion countRate(Integer countRate) {
        this.countRate = countRate;
        return this;
    }

    public void setCountRate(Integer countRate) {
        this.countRate = countRate;
    }

    public Integer getCountPassAgain() {
        return countPassAgain;
    }

    public LibQuestion countPassAgain(Integer countPassAgain) {
        this.countPassAgain = countPassAgain;
        return this;
    }

    public void setCountPassAgain(Integer countPassAgain) {
        this.countPassAgain = countPassAgain;
    }

    public Boolean isIsPass() {
        return isPass;
    }

    public LibQuestion isPass(Boolean isPass) {
        this.isPass = isPass;
        return this;
    }

    public void setIsPass(Boolean isPass) {
        this.isPass = isPass;
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
        LibQuestion libQuestion = (LibQuestion) o;
        if (libQuestion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), libQuestion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LibQuestion{" +
            "id=" + getId() +
            ", libName='" + getLibName() + "'" +
            ", ask='" + getAsk() + "'" +
            ", answer='" + getAnswer() + "'" +
            ", countPass=" + getCountPass() +
            ", countFail=" + getCountFail() +
            ", countRate=" + getCountRate() +
            ", countPassAgain=" + getCountPassAgain() +
            ", isPass='" + isIsPass() + "'" +
            "}";
    }
}
