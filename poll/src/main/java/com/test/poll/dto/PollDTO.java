package com.test.poll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PollDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp createdAt;

    @JsonProperty("open_questions")
    private List<OpenQuestionDTO> openQuestions;

    @JsonProperty("closed_questions")
    private List<ClosedQuestionDTO> closedQuestions;

    public PollDTO(String name, Timestamp createdAt, List<OpenQuestionDTO> openQuestions, List<ClosedQuestionDTO> closedQuestions) {
        this.name = name;
        this.createdAt = createdAt;
        this.openQuestions = openQuestions;
        this.closedQuestions = closedQuestions;
    }

    public PollDTO() {
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

    public List<OpenQuestionDTO> getOpenQuestions() {
        return openQuestions;
    }

    public void setOpenQuestions(List<OpenQuestionDTO> openQuestions) {
        this.openQuestions = openQuestions;
    }

    public List<ClosedQuestionDTO> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(List<ClosedQuestionDTO> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }
}
