package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.QuizCompletion;

public interface QuizCompletionData {

    QuizCompletion save(QuizCompletion quizCompletion);

    QuizCompletion findById(Long quizCompletionId);

    QuizCompletion findByQuizIdAndUserId(Long quizId, Long userId);
}
