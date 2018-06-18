package org.student.simplestomp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.student.simplestomp.entity.Message;
import org.student.simplestomp.entity.ReversedMessage;
import org.student.simplestomp.repository.ReversedMessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Controller
public class StompController {

  private final ReversedMessageRepository reversedMessageRepository;

  @MessageMapping("/string")
  @SendTo("/message/reversed")
  public List<ReversedMessage> reverseString(Message message) {
    log.info("receive: " + message.getString());
    StringBuffer sb = new StringBuffer(message.getString());

    try{
      reversedMessageRepository.save(ReversedMessage.builder().string(sb.reverse().toString()).build());
    } catch (Exception e) {
      log.error("save reversed message: " + e.toString());
      throw e;
    }

    List<ReversedMessage> answer = null;
    try{
      answer = reversedMessageRepository.findAll();
      log.info("send: " + answer.toString());
    } catch (Exception e) {
      log.error("get reversed messages: " + e.toString());
      throw e;
    }

    return answer;
  }
}
