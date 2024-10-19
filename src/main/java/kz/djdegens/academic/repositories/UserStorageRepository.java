package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.UserStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStorageRepository extends JpaRepository<UserStorage , Long> {

    UserStorage findByCourseIdAndStudentId(Long courseId, Long studentId);

    Integer countAllByCourseId(Long courseId);
}
