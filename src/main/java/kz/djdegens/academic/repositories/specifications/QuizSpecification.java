package kz.djdegens.academic.repositories.specifications;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.entities.Quiz;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class QuizSpecification {

    public static Specification<Quiz> findByLessonId(Long lessonId){
        return (root, query, criteriaBuilder) -> {
            if(Objects.isNull(lessonId)) return criteriaBuilder.conjunction();
            Join<Quiz, Lesson> lessonJoin = root.join("lesson", JoinType.INNER);
            return criteriaBuilder.equal(lessonJoin.get("id"),lessonId);
        };
    }

    public static Specification<Quiz> findByIsActive(Boolean isActive){
        return (root, query, criteriaBuilder) -> Objects.isNull(isActive) ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("isActive"),isActive);
    }

    public static Specification<Quiz> findByLessonIdAndIsActive(Long lessonId, Boolean isActive){
        return Specification.where(findByLessonId(lessonId))
                .and(findByIsActive(isActive));
    }
}
