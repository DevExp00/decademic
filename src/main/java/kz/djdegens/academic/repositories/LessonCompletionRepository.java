package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.LessonCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCompletionRepository extends JpaRepository<LessonCompletion, Long> {
}
