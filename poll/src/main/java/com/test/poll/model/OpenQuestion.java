package com.test.poll.model;

import javax.persistence.*;

@Entity
@Table(name = "open_question", schema = "polls_db")
public class OpenQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question")
    private String question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "poll_id", referencedColumnName = "id", nullable = false)
    private Poll openQuestionByPollId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Poll getOpenQuestionByPollId() {
        return openQuestionByPollId;
    }

    public void setOpenQuestionByPollId(Poll openQuestionByPollId) {
        this.openQuestionByPollId = openQuestionByPollId;
    }
}
