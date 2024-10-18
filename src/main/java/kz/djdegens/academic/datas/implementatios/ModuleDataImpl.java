package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.ModuleData;
import kz.djdegens.academic.entities.Module;
import kz.djdegens.academic.repositories.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModuleDataImpl implements ModuleData {

    private final ModuleRepository moduleRepository;

    @Override
    public Module save(Module module) {
        if(Objects.isNull(module))throw new IllegalArgumentException("Module can not be null");
        return moduleRepository.save(module);
    }


    @Override
    public Module findById(Long moduleId) {
        if(Objects.isNull(moduleId))throw new IllegalArgumentException("Module id can not be null");
        Optional<Module> optionalModule = moduleRepository.findById(moduleId);
        if(optionalModule.isEmpty())throw new NoSuchElementException("Module not found");
        return optionalModule.get();
    }

    @Override
    public List<Module> findAllByCourseId(Long courseId) {
        if(Objects.isNull(courseId))throw new IllegalArgumentException("Course id can not be null");
        return moduleRepository.findAllByCourseId(courseId);
    }
}
