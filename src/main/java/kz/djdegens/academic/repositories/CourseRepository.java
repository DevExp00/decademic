package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByCreatorId(Long creatorId);

}
