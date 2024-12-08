package kz.djdegens.academic.repositories.specifications;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.entities.Module;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class LessonSpecification {

    public static Specification<Lesson> findByModuleId(Long moduleId){
        return (root, query, criteriaBuilder) -> {
            if(Objects.isNull(moduleId))return criteriaBuilder.conjunction();
            Join<Lesson, Module> moduleJoin = root.join("module", JoinType.INNER);
            return criteriaBuilder.equal(moduleJoin.get("id"),moduleId);
        };
    }

    public static Specification<Lesson> findByIsActive(Boolean isActive){
        return (root, query, criteriaBuilder) -> Objects.isNull(isActive) ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("isActive"),isActive);
    }

    public static Specification<Lesson> findByModuleIdAndIsActive(Long moduleId, Boolean isActive){
        return Specification.where(findByModuleId(moduleId))
                .and(findByIsActive(isActive));
    }
}
