package com.test.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenQuestionDTO {
    @JsonProperty("question")
    private String question;

    public OpenQuestionDTO(String question) {
        this.question = question;
    }

    public OpenQuestionDTO() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
