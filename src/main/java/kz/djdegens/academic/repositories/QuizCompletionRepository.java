package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.QuizCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletionRepository extends JpaRepository<QuizCompletion, Long> {

    QuizCompletion findByQuizIdAndUserId(Long quizId, Long userId);
}
