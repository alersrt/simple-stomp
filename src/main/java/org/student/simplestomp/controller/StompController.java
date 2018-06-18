package org.student.simplestomp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.student.simplestomp.entity.Answer;
import org.student.simplestomp.entity.Request;
import org.student.simplestomp.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Controller
public class StompController {

  private final AnswerRepository answerRepository;

  @MessageMapping("/request")
  @SendTo("/answer")
  public List<Answer> reverseString(Request request) {
    log.info("receive: " + request.getString());
    StringBuffer sb = new StringBuffer(request.getString());

    try{
      answerRepository.save(Answer.builder().string(sb.reverse().toString()).build());
    } catch (Exception e) {
      log.error("save reversed request: " + e.toString());
      throw e;
    }

    List<Answer> answer = null;
    try{
      answer = answerRepository.findAll();
      log.info("send: " + answer.toString());
    } catch (Exception e) {
      log.error("get reversed messages: " + e.toString());
      throw e;
    }

    return answer;
  }
}
