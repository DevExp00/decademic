package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.AnswerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerHistoryRepository extends JpaRepository<AnswerHistory, Long> {
}
