package com.test.poll.service;

import com.test.poll.dto.AnswerDTO;
import com.test.poll.dto.ClosedQuestionDTO;
import com.test.poll.dto.OpenQuestionDTO;
import com.test.poll.dto.PollDTO;
import com.test.poll.exception.ApiException;
import com.test.poll.model.Answer;
import com.test.poll.model.ClosedQuestion;
import com.test.poll.model.OpenQuestion;
import com.test.poll.model.Poll;
import com.test.poll.repository.IPollsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollsServiceImpl implements IPollsService {
    private final IPollsRepository pollsRepository;
    private final ModelMapper modelMapper;

    public PollsServiceImpl(IPollsRepository pollsRepository, ModelMapper modelMapper) {
        this.pollsRepository = pollsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArrayList<PollDTO> findAllPolls() {
        List<Poll> polls = pollsRepository.findAll();

        if (polls.isEmpty()) {
            throw new ApiException("not found", "there aren't any poll saved", 404);
        }

        ArrayList<PollDTO> pollsDTO = new ArrayList<>();
        for (Poll poll : polls) {
            pollsDTO.add(mapPollToPollDTO(poll));
        }
        return pollsDTO;
    }

    @Override
    public PollDTO findPollById(Integer id) {
        Poll poll = pollsRepository.findByIdEquals(id);

        if (poll == null) {
            throw new ApiException("not found", "poll with id " + id + " not found", 404);
        }

        return mapPollToPollDTO(poll);
    }

    @Override
    public String savePoll(PollDTO pollDTO) {
        Poll poll = new Poll();
        poll.setName(pollDTO.getName());
        poll.setCreatedAt(pollDTO.getCreatedAt());

        if (pollDTO.getClosedQuestions() != null) {
            ArrayList<ClosedQuestion> closedQuestions = new ArrayList<>();
            for (ClosedQuestionDTO closedQuestionDTO : pollDTO.getClosedQuestions()) {
                ArrayList<Answer> answers = new ArrayList<>();
                for (AnswerDTO answerDTO : closedQuestionDTO.getAnswers()) {
                    answers.add(modelMapper.map(answerDTO, Answer.class));
                }
                closedQuestions.add(modelMapper.map(closedQuestionDTO, ClosedQuestion.class));
            }
            poll.setClosedQuestions(closedQuestions);
        }

        if (pollDTO.getOpenQuestions() != null) {
            ArrayList<OpenQuestion> openQuestions = new ArrayList<>();
            for (OpenQuestionDTO openQuestionDTO : pollDTO.getOpenQuestions()) {
                openQuestions.add(modelMapper.map(openQuestionDTO, OpenQuestion.class));
            }
            poll.setOpenQuestions(openQuestions);
        }

        pollsRepository.save(poll);

        return "Poll successfully saved";
    }

    private PollDTO mapPollToPollDTO(Poll poll) {
        PollDTO pollDTO = new PollDTO();
        pollDTO.setName(poll.getName());
        pollDTO.setCreatedAt(poll.getCreatedAt());

        if (poll.getClosedQuestions() != null) {
            ArrayList<ClosedQuestionDTO> closedQuestions = new ArrayList<>();
            for (ClosedQuestion closedQuestion : poll.getClosedQuestions()) {
                ArrayList<AnswerDTO> answers = new ArrayList<>();
                for (Answer answer : closedQuestion.getAnswers()) {
                    answers.add(modelMapper.map(answer, AnswerDTO.class));
                }
                closedQuestions.add(modelMapper.map(closedQuestion, ClosedQuestionDTO.class));
            }
            pollDTO.setClosedQuestions(closedQuestions);
        }

        if (poll.getOpenQuestions() != null) {
            ArrayList<OpenQuestionDTO> openQuestions = new ArrayList<>();
            for (OpenQuestion openQuestion : poll.getOpenQuestions()) {
                openQuestions.add(modelMapper.map(openQuestion, OpenQuestionDTO.class));
            }
            pollDTO.setOpenQuestions(openQuestions);
        }

        return pollDTO;
    }
}

