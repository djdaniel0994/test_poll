package com.test.poll.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "closed_question", schema = "polls_db")
public class ClosedQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question")
    private String question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "poll_id", referencedColumnName = "id", nullable = false)
    private Poll closedQuestionByPollId;

    @OneToMany(mappedBy = "closedQuestionId", cascade = CascadeType.ALL)
    private List<Answer> answers;

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

    public Poll getClosedQuestionByPollId() {
        return closedQuestionByPollId;
    }

    public void setClosedQuestionByPollId(Poll closedQuestionByPollId) {
        this.closedQuestionByPollId = closedQuestionByPollId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
        answers.forEach(closedQuestion -> closedQuestion.setClosedQuestionId(this));
    }
}
