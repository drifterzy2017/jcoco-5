package com.kkk.cocoapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class Question implements Serializable {

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

    @ManyToOne
//    @JsonIgnoreProperties("questions")
    @JsonIgnore
    private Quiz quiz;

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

    public Question libName(String libName) {
        this.libName = libName;
        return this;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getAsk() {
        return ask;
    }

    public Question ask(String ask) {
        this.ask = ask;
        return this;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAnswer() {
        return answer;
    }

    public Question answer(String answer) {
        this.answer = answer;
        return this;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCountPass() {
        return countPass;
    }

    public Question countPass(Integer countPass) {
        this.countPass = countPass;
        return this;
    }

    public void setCountPass(Integer countPass) {
        this.countPass = countPass;
    }

    public Integer getCountFail() {
        return countFail;
    }

    public Question countFail(Integer countFail) {
        this.countFail = countFail;
        return this;
    }

    public void setCountFail(Integer countFail) {
        this.countFail = countFail;
    }

    public Integer getCountRate() {
        return countRate;
    }

    public Question countRate(Integer countRate) {
        this.countRate = countRate;
        return this;
    }

    public void setCountRate(Integer countRate) {
        this.countRate = countRate;
    }

    public Integer getCountPassAgain() {
        return countPassAgain;
    }

    public Question countPassAgain(Integer countPassAgain) {
        this.countPassAgain = countPassAgain;
        return this;
    }

    public void setCountPassAgain(Integer countPassAgain) {
        this.countPassAgain = countPassAgain;
    }

    public Boolean isIsPass() {
        return isPass;
    }

    public Question isPass(Boolean isPass) {
        this.isPass = isPass;
        return this;
    }

    public void setIsPass(Boolean isPass) {
        this.isPass = isPass;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public Question quiz(Quiz quiz) {
        this.quiz = quiz;
        return this;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
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
        Question question = (Question) o;
        if (question.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Question{" +
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
