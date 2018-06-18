package org.student.simplestomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.student.simplestomp.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
}
