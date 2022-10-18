package com.test.poll.unit.service;

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
import com.test.poll.service.PollsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PollsServiceImplTest {
    @Mock
    IPollsRepository pollsRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PollsServiceImpl pollsService;

    @Test
    void GetPollById_Success() throws ParseException {
        List<OpenQuestionDTO> openQuestionsDTO = new ArrayList<>();
        openQuestionsDTO.add(new OpenQuestionDTO("escribe tu feedback"));

        List<AnswerDTO> answersDTO = new ArrayList<>();
        answersDTO.add(new AnswerDTO("Si"));
        answersDTO.add(new AnswerDTO("No"));

        List<ClosedQuestionDTO> closedQuestionsDTO = new ArrayList<>();
        closedQuestionsDTO.add(new ClosedQuestionDTO("volverías a usar nuestro servicio", answersDTO));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        Date date = sdf.parse("2022-10-17 00:00:00");
        Timestamp time = new Timestamp(date.getTime());
        PollDTO expectedPoll = new PollDTO("Encuesta 1", time, openQuestionsDTO, closedQuestionsDTO);

        Poll poll = new Poll();
        poll.setId(1);
        poll.setName("Encuesta 1");
        poll.setCreatedAt(time);

        List<OpenQuestion> openQuestions = new ArrayList<>();
        OpenQuestion openQuestion = new OpenQuestion();
        openQuestion.setId(1);
        openQuestion.setQuestion("escribe tu feedback");
        openQuestion.setOpenQuestionByPollId(poll);
        openQuestions.add(openQuestion);

        List<ClosedQuestion> closedQuestions = new ArrayList<>();
        ClosedQuestion closedQuestion = new ClosedQuestion();
        closedQuestion.setId(1);
        closedQuestion.setQuestion("volverías a usar nuestro servicio");
        closedQuestion.setClosedQuestionByPollId(poll);

        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer();
        answer1.setId(1);
        answer1.setText("Si");
        answer1.setClosedQuestionId(closedQuestion);
        Answer answer2 = new Answer();
        answer2.setId(2);
        answer2.setText("No");
        answer2.setClosedQuestionId(closedQuestion);
        answers.add(answer1);
        answers.add(answer2);

        closedQuestion.setAnswers(answers);
        closedQuestions.add(closedQuestion);

        poll.setOpenQuestions(openQuestions);
        poll.setClosedQuestions(closedQuestions);

        Mockito.when(pollsRepository.findByIdEquals(any())).thenReturn(poll);
        Mockito.when(modelMapper.map(answer1, AnswerDTO.class)).thenReturn(new AnswerDTO("Si"));
        Mockito.when(modelMapper.map(answer2, AnswerDTO.class)).thenReturn(new AnswerDTO("No"));
        Mockito.when(modelMapper.map(closedQuestion, ClosedQuestionDTO.class))
                .thenReturn(new ClosedQuestionDTO("volverías a usar nuestro servicio", answersDTO));
        Mockito.when(modelMapper.map(openQuestion, OpenQuestionDTO.class))
                .thenReturn(new OpenQuestionDTO("escribe tu feedback"));

        PollDTO returnedPoll = pollsService.findPollById(1);

        assertEquals(expectedPoll, returnedPoll);
    }

    @Test
    void GetPollById_NotFound() {
        Mockito.when(pollsRepository.findByIdEquals(any())).thenReturn(null);

        assertThrows(ApiException.class, () -> pollsService.findPollById(1));
    }

    @Test
    void SavePoll_Success() throws ParseException {
        List<OpenQuestionDTO> openQuestionsDTO = new ArrayList<>();
        openQuestionsDTO.add(new OpenQuestionDTO("escribe tu feedback"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        Date date = sdf.parse("2022-10-17 00:00:00");
        Timestamp time = new Timestamp(date.getTime());
        PollDTO pollDTO = new PollDTO("Encuesta 1", time, openQuestionsDTO, null);

        OpenQuestion openQuestion = new OpenQuestion();
        openQuestion.setQuestion("escribe tu feedback");

        Mockito.when(modelMapper.map(new OpenQuestionDTO("escribe tu feedback"), OpenQuestion.class)).thenReturn(openQuestion);

        Mockito.when(pollsRepository.save(any())).thenReturn(new Poll());

        String actual = pollsService.savePoll(pollDTO);

        assertEquals("Poll successfully saved", actual);
    }
}
