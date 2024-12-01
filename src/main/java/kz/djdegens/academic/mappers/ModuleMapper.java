package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.LessonDto;
import kz.djdegens.academic.dtos.ModuleDto;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.entities.Module;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ModuleMapper {

    public Module dtoToEntity(ModuleDto moduleDto, Course course){
        if(Objects.isNull(moduleDto))throw new IllegalArgumentException("Module dto can not be null");
        if(Objects.isNull(course))throw new IllegalArgumentException("Course can not be null");
        Module module = new Module();
        module.setCourse(course);
        module.setTitle(moduleDto.getTitle() == null ? null : moduleDto.getTitle());
        module.setDescription(moduleDto.getDescription() == null ? null : moduleDto.getDescription());
        module.setPointsToPass(moduleDto.getPointsToPass() == null ? null : moduleDto.getPointsToPass());
        module.setOrder(moduleDto.getOrderBy() == null ? null : moduleDto.getOrderBy());
        return module;
    }

    public ModuleDto entityToDto(Module module){
        if(Objects.isNull(module))throw new IllegalArgumentException("Module can not be null");
        ModuleDto moduleDto = new ModuleDto();
        moduleDto.setId(module.getId());
        moduleDto.setTitle(module.getTitle() == null ? null : module.getTitle());
        moduleDto.setDescription(module.getDescription() == null ? null : module.getDescription());
        moduleDto.setPointsToPass(module.getPointsToPass() == null ? null : module.getPointsToPass());
        moduleDto.setOrderBy(module.getOrder() == null ? null : module.getOrder());
        return moduleDto;
    }

    public List<ModuleDto> entityToDto(List<Module> modules){
        List<ModuleDto> moduleDtos = new ArrayList<>();
        for(Module module : modules){
            ModuleDto moduleDto = new ModuleDto();
            moduleDto.setId(module.getId());
            moduleDto.setTitle(module.getTitle() == null ? null : module.getTitle());
            moduleDto.setDescription(module.getDescription() == null ? null : module.getDescription());
            moduleDto.setPointsToPass(module.getPointsToPass() == null ? null : module.getPointsToPass());
            moduleDto.setOrderBy(module.getOrder() == null ? null : module.getOrder());
            moduleDtos.add(moduleDto);
        }
        return moduleDtos;
    }

    public ModuleDto entityToDto(Module module, List<LessonDto> lessonDtos){
            ModuleDto moduleDto = new ModuleDto();
            moduleDto.setId(module.getId());
            moduleDto.setTitle(module.getTitle() == null ? null : module.getTitle());
            moduleDto.setDescription(module.getDescription() == null ? null : module.getDescription());
            moduleDto.setPointsToPass(module.getPointsToPass() == null ? null : module.getPointsToPass());
            moduleDto.setOrderBy(module.getOrder() == null ? null : module.getOrder());
            moduleDto.setLessons(lessonDtos);
        return moduleDto;
    }

    public Module dtoToEntity(Module module, ModuleDto moduleDto){
        if(Objects.isNull(module))throw new IllegalArgumentException("Module can not be null");
        if(Objects.isNull(moduleDto))throw new IllegalArgumentException("ModuleDto can not be null");
        module.setTitle(moduleDto.getTitle() == null ? module.getTitle() : moduleDto.getTitle());
        module.setDescription(moduleDto.getDescription() == null ? module.getDescription() : moduleDto.getDescription());
        module.setOrder(moduleDto.getOrderBy() == null ? module.getOrder() : moduleDto.getOrderBy());
        module.setPointsToPass(moduleDto.getPointsToPass() == null ? module.getPointsToPass() : moduleDto.getPointsToPass());
        module.setIsActive(moduleDto.getIsActive() == null ? module.getIsActive() : moduleDto.getIsActive());
        return module;
    }
}
