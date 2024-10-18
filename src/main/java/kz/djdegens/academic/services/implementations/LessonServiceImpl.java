package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.LessonData;
import kz.djdegens.academic.datas.interfaces.ModuleData;
import kz.djdegens.academic.dtos.LessonDto;
import kz.djdegens.academic.entities.Module;
import kz.djdegens.academic.mappers.LessonMapper;
import kz.djdegens.academic.mappers.ModuleMapper;
import kz.djdegens.academic.services.interfaces.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final ModuleData moduleData;
    private final LessonData lessonData;
    private final LessonMapper lessonMapper;

    @Override
    public void addLesson(LessonDto lessonDto) {
        if(lessonDto.getModuleId()==null)throw new IllegalArgumentException("Module id can not be null");
        Module module = moduleData.findById(lessonDto.getModuleId());
        lessonData.save(lessonMapper.dtoToEntity(lessonDto,module));
    }
}
