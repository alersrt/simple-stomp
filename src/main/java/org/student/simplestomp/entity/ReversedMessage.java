package org.student.simplestomp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class ReversedMessage {

  @Id private String id;

  @NonNull private String reversedString;
}
