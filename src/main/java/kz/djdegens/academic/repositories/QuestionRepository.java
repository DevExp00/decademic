package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByQuizId(Long quizId);

}
