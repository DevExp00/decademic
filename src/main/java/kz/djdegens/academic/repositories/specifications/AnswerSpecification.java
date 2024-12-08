package kz.djdegens.academic.repositories.specifications;

import javax.persistence.*;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

import kz.djdegens.academic.entities.Answer;
import kz.djdegens.academic.entities.Question;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class AnswerSpecification {

    public static Specification<Answer> findByQuestionId(Long questionId){
        return (root, query, criteriaBuilder) -> {
            if(Objects.isNull(questionId)) return criteriaBuilder.conjunction();
            Join<Answer, Question> questionJoin = root.join("question", JoinType.INNER);
            return criteriaBuilder.equal(questionJoin.get("id"),questionId);
        };
    }

    public static Specification<Answer> findByIsActive(Boolean isActive){
        return (root, query, criteriaBuilder) -> Objects.isNull(isActive) ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("isActive"),isActive);
    }

    public static Specification<Answer> findByQuestionIdAndIsActive(Long questionId,Boolean isActive){
        return Specification.where(findByQuestionId(questionId))
                .and(findByIsActive(isActive));
    }
}
