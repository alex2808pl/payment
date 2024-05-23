package de.telran.payment.entity.repository;

import de.telran.payment.entity.Senders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendersRepository extends JpaRepository<Senders, Long> {
}
