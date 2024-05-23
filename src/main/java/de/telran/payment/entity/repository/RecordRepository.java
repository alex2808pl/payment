package de.telran.payment.entity.repository;

import de.telran.payment.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Note, Long> {
}
