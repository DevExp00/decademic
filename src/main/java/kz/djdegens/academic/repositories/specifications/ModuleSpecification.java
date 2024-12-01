package kz.djdegens.academic.repositories.specifications;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.entities.Module;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class ModuleSpecification {

    public static Specification<Module> findByIsActive(Boolean isActive){
        return (root, query, criteriaBuilder) -> Objects.isNull(isActive) ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("isActive"),isActive);
    }

    public static Specification<Module> findByCourseId(Long courseId){
        return (root, query, criteriaBuilder) -> {
            if(Objects.isNull(courseId)) return criteriaBuilder.conjunction();
            Join<Module , Course> courseJoin = root.join("course", JoinType.INNER);
            return criteriaBuilder.equal(courseJoin.get("id"),courseId);
        };
    }
}
