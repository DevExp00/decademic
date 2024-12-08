package kz.djdegens.academic.repositories.specifications;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.entities.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class CourseSpecification {

    public static Specification<Course> findByIsActive(Boolean isActive){
        return (root, query, criteriaBuilder) -> Objects.isNull(isActive) ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("isActive"),isActive);
    }

    public static Specification<Course> findByCreatorId(Long creatorId){
        return (root, query, criteriaBuilder) -> {
            if(Objects.isNull(creatorId)) return criteriaBuilder.conjunction();
            Join<Course, User> userJoin = root.join("creator", JoinType.INNER);
            return criteriaBuilder.equal(userJoin.get("id"),creatorId);
        };
    }

}
