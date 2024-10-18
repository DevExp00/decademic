package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.CourseData;
import kz.djdegens.academic.datas.interfaces.ModuleData;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.dtos.ModuleDto;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.entities.Module;
import kz.djdegens.academic.mappers.ModuleMapper;
import kz.djdegens.academic.services.interfaces.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final CourseData courseData;
    private final ModuleData moduleData;
    private final ModuleMapper moduleMapper;

    @Override
    public void addModule(ModuleDto moduleDto) {
        if(moduleDto.getCourseId()==null)throw new IllegalArgumentException("Course id can not be null");
        Course course = courseData.findById(moduleDto.getCourseId());
        moduleData.save(moduleMapper.dtoToEntity(moduleDto,course));
    }
}
