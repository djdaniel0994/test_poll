package com.test.poll.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "poll", schema = "polls_db")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "openQuestionByPollId", cascade = CascadeType.ALL)
    private List<OpenQuestion> openQuestions;

    @OneToMany(mappedBy = "closedQuestionByPollId", cascade = CascadeType.ALL)
    private List<ClosedQuestion> closedQuestions;

    public Poll(Integer id, String name, Timestamp createdAt, List<OpenQuestion> openQuestions, List<ClosedQuestion> closedQuestions) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.openQuestions = openQuestions;
        this.closedQuestions = closedQuestions;
    }

    public Poll() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<OpenQuestion> getOpenQuestions() {
        return openQuestions;
    }

    public void setOpenQuestions(List<OpenQuestion> openQuestions) {
        this.openQuestions = openQuestions;
        openQuestions.forEach(openQuestion -> openQuestion.setOpenQuestionByPollId(this));
    }

    public List<ClosedQuestion> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(List<ClosedQuestion> closedQuestions) {
        this.closedQuestions = closedQuestions;
        closedQuestions.forEach(closedQuestion -> closedQuestion.setClosedQuestionByPollId(this));
    }
}
