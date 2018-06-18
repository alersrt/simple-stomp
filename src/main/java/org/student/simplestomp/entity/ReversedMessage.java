package org.student.simplestomp.entity;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class ReversedMessage {

  @NonNull
  private String reversedString;
}
