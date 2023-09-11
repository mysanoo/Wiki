package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Answer;
import com.example.WikiHow.entity.Question;
import com.example.WikiHow.entity.User;
import com.example.WikiHow.repo.AnswerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    @Mock
    private AnswerRepo answerRepo;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private QuestionServiceImpl questionService;
    @InjectMocks
    private AnswerServiceImpl answerService;

    @BeforeEach
    void setUp() {
        answerService = new AnswerServiceImpl(answerRepo,questionService,userService);
    }

    @Test
    void createAnswer() {
        Answer mockAnswer = new Answer();
        mockAnswer.setText("Sample answer");

        Question mockQuestion = new Question();
        mockQuestion.setId(1);
        mockQuestion.setText("Sample question");

        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("Sample user");

        mockAnswer.setQuestion(mockQuestion);

        // Configure mock behavior
        when(questionService.theQuestion(1)).thenReturn(mockQuestion);
        when(userService.theUser(1)).thenReturn(mockUser);

        // Call the method under test
        ApiResponse result = answerService.createAnswer(mockAnswer, 1);

        // Verify the correct methods were called
        org.mockito.Mockito.verify(questionService).theQuestion(1);
        org.mockito.Mockito.verify(userService).theUser(1);

        // Assert the result
        Answer createdAnswer = (Answer) result.getData();
        assertEquals("Sample answer", createdAnswer.getText());
        assertEquals(mockQuestion, createdAnswer.getQuestion());
        assertEquals(mockUser, createdAnswer.getUsers());
    }

    @Test
    void updateAnswerText() {
        TextDto mockDto = new TextDto("Changed text");
        Answer answer = Answer.builder()
                .id(1)
                .text("Sample text")
                .build();

        when(answerRepo.findById(1)).thenReturn(Optional.of(answer));

        ApiResponse result = answerService.updateAnswerText(1, mockDto);

        verify(answerRepo).save(answer);
        verify(answerRepo).findById(1);

        assertEquals(answer.getText(), mockDto.getText());
        assertEquals(result.getData(), answer);
    }

    @Test
    void deleteAnswer() {
        Answer mockAnswer = Answer.builder()
                .text("simple answer")
                .id(1)
                .build();

        when(answerRepo.findById(1)).thenReturn(Optional.of(mockAnswer));

        ApiResponse result = answerService.deleteAnswer(1);

        verify(answerRepo).findById(1);
        verify(answerRepo).delete(mockAnswer);

        assertEquals(mockAnswer, result.getData());


    }

    @Test
    void getQuestionAnswers() {

        Question question  = Question.builder().id(1).text("first question").build();


        Answer mockAnswer1 = Answer.builder().id(1).text("first mock answer").question(question).build();
        Answer mockAnswer2 = Answer.builder().id(2).text("second mock answer").question(question).build();
        Answer mockAnswer3 = Answer.builder().id(3).text("third mock answer").question(question).build();

        List<Answer> mockAnswers = Arrays.asList(mockAnswer1,mockAnswer2,mockAnswer3);

        when(answerRepo.findAll()).thenReturn(mockAnswers);

        ApiResponse result = answerService.getQuestionAnswers(1);

        verify(answerRepo).findAll();

        List<Answer> questionAnswers = (List<Answer>) result.getData();
        assertEquals(questionAnswers.size(), 3);
        assertEquals(mockAnswer1, questionAnswers.get(0));
    }
}