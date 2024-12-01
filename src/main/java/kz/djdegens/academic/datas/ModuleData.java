package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.Module;
import kz.djdegens.academic.repositories.ModuleRepository;
import kz.djdegens.academic.repositories.specifications.ModuleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModuleData {

    private final ModuleRepository moduleRepository;

    public Module save(Module module) {
        if(Objects.isNull(module))throw new IllegalArgumentException("Module can not be null");
        return moduleRepository.save(module);
    }

    public Module findById(Long moduleId) {
        if(Objects.isNull(moduleId))throw new IllegalArgumentException("Module id can not be null");
        Optional<Module> optionalModule = moduleRepository.findById(moduleId);
        if(optionalModule.isEmpty())throw new NoSuchElementException("Module not found");
        return optionalModule.get();
    }

    public List<Module> findAllByCourseId(Long courseId) {
        if(Objects.isNull(courseId))throw new IllegalArgumentException("Course id can not be null");
        return moduleRepository.findAll(Specification.where(ModuleSpecification.findByCourseId(courseId)).and(ModuleSpecification.findByIsActive(true)));
    }

    public Integer countAllByCourseId(Long courseId) {
        if(Objects.isNull(courseId))throw new IllegalArgumentException("Course id can not be null");
        return moduleRepository.countAllByCourseId(courseId);
    }
}
