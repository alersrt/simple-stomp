package org.student.simplestomp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.student.simplestomp.entity.Answer;
import org.student.simplestomp.entity.Request;
import org.student.simplestomp.repository.AnswerRepository;

@Tag("unit")
@DisplayName("Test for STOMP controller")
class StompControllerTest {

  private AnswerRepository answerRepository = mock(AnswerRepository.class);

  private StompController stompController = new StompController(answerRepository);

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @AfterEach
  void tearDown() {}

  @DisplayName("reverse string: normally way")
  @Test
  void reverseString_ok() {
    /*------ Arranges ------*/
    List<Answer> expectedAnswer = new ArrayList<>();
    expectedAnswer.add(Answer.builder().string("tsrif").build());
    expectedAnswer.add(Answer.builder().string("dnoces").build());

    when(answerRepository.save(any(Answer.class)))
        .then(
            invocation -> {
              expectedAnswer.add(invocation.getArgument(0));
              return invocation.getArgument(0);
            });
    when(answerRepository.findAll()).thenReturn(expectedAnswer);

    /*------ Actions ------*/
    List<Answer> result = stompController.reverseString(Request.builder().string("third").build());

    /*------ Asserts ------*/
    assertAll(
        "Check result array",
        () -> assertThat(result.get(2).getString()).isEqualTo("driht"),
        () -> assertThat(result.get(1).getString()).isEqualTo("dnoces"),
        () -> assertThat(result.get(0).getString()).isEqualTo("tsrif"));
  }

  @DisplayName("reverse string: exception during saving")
  @Test
  void reverseString_save_exception() {
    /*------ Arranges ------*/
    when(answerRepository.save(any(Answer.class))).thenThrow(new RuntimeException("test"));

    /*------ Actions ------*/
    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              stompController.reverseString(Request.builder().string("first").build());
            });

    /*------ Asserts ------*/
    assertThat(exception.getMessage()).isEqualTo("test");
  }

  @DisplayName("reverse string: exception during findAll")
  @Test
  void reverseString_findAll_exception() {
    /*------ Arranges ------*/
    when(answerRepository.save(any(Answer.class))).then(invocation -> invocation.getArgument(0));
    when(answerRepository.findAll()).thenThrow(new RuntimeException("test"));

    /*------ Actions ------*/
    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              stompController.reverseString(Request.builder().string("first").build());
            });

    /*------ Asserts ------*/
    assertThat(exception.getMessage()).isEqualTo("test");
  }
}
