package de.telran.payment.repository;

import de.telran.payment.entity.Recipients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientsRepository extends JpaRepository<Recipients, Long> {
}
