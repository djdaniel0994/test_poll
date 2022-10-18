package com.test.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AnswerDTO {
    @JsonProperty("text")
    private String text;

    public AnswerDTO(String text) {
        this.text = text;
    }

    public AnswerDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
