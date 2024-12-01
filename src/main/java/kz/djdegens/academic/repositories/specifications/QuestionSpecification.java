package kz.djdegens.academic.repositories.specifications;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import kz.djdegens.academic.entities.Question;
import kz.djdegens.academic.entities.Quiz;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class QuestionSpecification {

    public static Specification<Question> findByQuizId(Long quizId){
        return (root, query, criteriaBuilder) -> {
            if(Objects.isNull(quizId)) return criteriaBuilder.conjunction();
            Join<Question, Quiz> quizJoin = root.join("quiz", JoinType.INNER);
            return criteriaBuilder.equal(quizJoin.get("id"),quizId);
        };
    }

    public static Specification<Question> findByIsActive(Boolean isActive){
        return (root, query, criteriaBuilder) -> Objects.isNull(isActive) ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("isActive"),isActive);
    }

    public static Specification<Question> findByQuizIdAndIsActive(Long quizId, Boolean isActive){
        return Specification.where(findByQuizId(quizId))
                .and(findByIsActive(isActive));
    }
}
