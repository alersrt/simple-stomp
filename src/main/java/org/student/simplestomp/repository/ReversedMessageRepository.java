package org.student.simplestomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.student.simplestomp.entity.ReversedMessage;

@Repository
public interface ReversedMessageRepository extends JpaRepository<ReversedMessage, String> {
}
