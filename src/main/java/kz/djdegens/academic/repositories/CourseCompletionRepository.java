package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.CourseCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCompletionRepository extends JpaRepository<CourseCompletion, Long> {
}
