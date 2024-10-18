package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.ModuleDto;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.entities.Module;
import org.springframework.stereotype.Component;

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
}
