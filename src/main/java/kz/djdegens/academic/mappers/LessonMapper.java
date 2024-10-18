package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.LessonDto;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.entities.Module;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LessonMapper {

    public Lesson dtoToEntity(LessonDto lessonDto, Module module){
        if(Objects.isNull(lessonDto))throw new IllegalArgumentException("Lesson dto can not be null");
        if(Objects.isNull(module))throw new IllegalArgumentException("Module can not be null");
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDto.getTitle() == null ? null : lessonDto.getTitle());
        lesson.setDescription(lessonDto.getDescription() == null ? null : lessonDto.getDescription());
        lesson.setModule(module);
        lesson.setContentType(lessonDto.getContentType() == null ? null : lessonDto.getContentType());
        lesson.setContent(lessonDto.getContent() == null ? null : lessonDto.getContent());
        lesson.setOrder(lessonDto.getOrder() == null ? null : lessonDto.getOrder());
        lesson.setPointsToPass(lessonDto.getPointsToPass() == null ? null : lessonDto.getPointsToPass());
        return lesson;
    }
}
