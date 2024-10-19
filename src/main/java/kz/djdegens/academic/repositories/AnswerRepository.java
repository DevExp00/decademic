package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer findByQuestionIdAndIsCorrect(Long questionId, Boolean isCorrect);
}
