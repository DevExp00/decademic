package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.LessonCompletion;
import kz.djdegens.academic.entities.QuizCompletion;

import java.util.List;

public interface QuizCompletionData {

    QuizCompletion save(QuizCompletion quizCompletion);

    QuizCompletion findById(Long quizCompletionId);

    QuizCompletion findByQuizIdAndUserId(Long quizId, Long userId);

    List<QuizCompletion> findAllByQuizIdAndLessonCompletionAndUserIdAndIsPassed(Long quizId, LessonCompletion lessonCompletion, Long userId, Boolean isPassed);
}
