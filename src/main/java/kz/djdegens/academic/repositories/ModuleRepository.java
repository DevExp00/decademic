package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>, JpaSpecificationExecutor<Module> {

    List<Module> findAllByCourseId(Long courseId);

    Integer countAllByCourseId(Long courseId);

}
