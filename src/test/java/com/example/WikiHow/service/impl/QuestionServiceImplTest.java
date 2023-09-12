package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.Question;
import com.example.WikiHow.entity.User;
import com.example.WikiHow.repo.QuestionRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    private QuestionServiceImpl underTest;

    @Mock
    private QuestionRepo questionRepo;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private PostServiceImpl postService;

    @BeforeEach
    void beforeEach(){
        underTest = new QuestionServiceImpl(questionRepo,userService, postService);
    }

    @Test
    void createQuestion() {
       User mockUser = User.builder().id(1).build();
       Post mockPost = Post.builder().id(1).build();
       Question mockQuestion = Question.builder().user(mockUser).text("text").id(1).build();


       when(userService.theUser(1)).thenReturn(mockUser);
       when(postService.thePost(1)).thenReturn(mockPost);

       ApiResponse result = underTest.createQuestion(mockQuestion, 1);

       verify(userService).theUser(1);
       verify(postService).thePost(1);

       Question question = (Question) result.getData();
       assertEquals(question.getText(), mockQuestion.getText());
       assertEquals(question.getUser(), mockUser);
       assertEquals(question.getPost(), mockPost);
    }

    @Test
    void theQuestion() {
        Question question = Question.builder().id(1).build();

        when(questionRepo.findById(1)).thenReturn(Optional.of(question));

        Question result = underTest.theQuestion(1);

        verify(questionRepo).findById(1);

        assertEquals(result, question);
    }

    @Test
    void deleteQuestion() {
        Question mockQuestion = Question.builder().id(1).build();

        when(questionRepo.findById(1)).thenReturn(Optional.of(mockQuestion));

        ApiResponse result = underTest.deleteQuestion(1);

        verify(questionRepo).delete(mockQuestion);

        assertEquals(mockQuestion, result.getData());


    }

    @Test
    void updateQuestionText() {
        Question mockQuestion = Question.builder().id(1).build();
        TextDto dto = new TextDto("sample question");

        when(questionRepo.findById(1)).thenReturn(Optional.of(mockQuestion));

        ApiResponse result = underTest.updateQuestionText(dto,1);

        verify(questionRepo).save(mockQuestion);
        verify(questionRepo).findById(1);

        assertEquals(mockQuestion, result.getData());
    }

    @Test
    void helpFull() {
       Question question = Question.builder().id(1).helpful(null).build();

       when(questionRepo.findById(1)).thenReturn(Optional.of(question));

       ApiResponse result = underTest.helpFull(1);

       verify(questionRepo).findById(1);
       verify(questionRepo).save(question);

       Question updatedQuestion = (Question) result.getData();

       assertEquals(1, updatedQuestion.getHelpful());

       result = underTest.helpFull(1);
       updatedQuestion = (Question) result.getData();

       assertEquals(2, updatedQuestion.getHelpful());
    }

    @Test
    void notHelpFull() {
        Question question = Question.builder().id(1).not_helpful(null).build();

        when(questionRepo.findById(1)).thenReturn(Optional.of(question));

        ApiResponse result = underTest.NotHelpFull(1);

        verify(questionRepo).findById(1);
        verify(questionRepo).save(question);

        Question updatedQuestion = (Question) result.getData();

        assertEquals(1, updatedQuestion.getNot_helpful());

        result = underTest.NotHelpFull(1);
        updatedQuestion = (Question) result.getData();

        assertEquals(2, updatedQuestion.getNot_helpful());
    }
}