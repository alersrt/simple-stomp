package org.student.simplestomp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.student.simplestomp.entity.ReversedMessage;
import org.student.simplestomp.repository.ReversedMessageRepository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class StompController {

  private final ReversedMessageRepository reversedMessageRepository;

  @MessageMapping("/string")
  @SendTo("/message/reversed")
  public JsonObject reverseString(JsonObject message) {
    StringBuffer sb = new StringBuffer(message.get("string").getAsString());

    reversedMessageRepository.save(new ReversedMessage(sb.reverse().toString()));
    List<ReversedMessage> messages = reversedMessageRepository.findAll();

    JsonObject answer = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    messages.forEach(m -> {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("string", m.getReversedString());
      jsonArray.add(jsonObject);
    });
    answer.add("content", jsonArray);
    return answer;
  }
}