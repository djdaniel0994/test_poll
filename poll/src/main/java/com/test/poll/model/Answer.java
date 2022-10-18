package com.test.poll.model;

import javax.persistence.*;

@Entity
@Table(name = "answer", schema = "polls_db")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "closed_question_id", referencedColumnName = "id", nullable = false)
    private ClosedQuestion closedQuestionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ClosedQuestion getClosedQuestionId() {
        return closedQuestionId;
    }

    public void setClosedQuestionId(ClosedQuestion closedQuestionId) {
        this.closedQuestionId = closedQuestionId;
    }
}
