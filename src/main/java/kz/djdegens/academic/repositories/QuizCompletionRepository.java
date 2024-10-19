package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.LessonCompletion;
import kz.djdegens.academic.entities.QuizCompletion;
import kz.djdegens.academic.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizCompletionRepository extends JpaRepository<QuizCompletion, Long> {

    QuizCompletion findByQuizIdAndUserId(Long quizId, Long userId);

    List<QuizCompletion> findAllByQuizIdAndLessonCompletionAndUserIdAndIsPassed(Long quizId, LessonCompletion lessonCompletion, Long userId, Boolean isPassed);
}
