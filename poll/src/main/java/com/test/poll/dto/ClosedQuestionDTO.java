package com.test.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ClosedQuestionDTO {
    @JsonProperty("question")
    private String question;

    @JsonProperty("answers")
    private List<AnswerDTO> answers;

    public ClosedQuestionDTO() {
    }

    public ClosedQuestionDTO(String question, List<AnswerDTO> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
