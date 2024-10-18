package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.ModuleCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleCompletionRepository extends JpaRepository<ModuleCompletion, Long> {
}
