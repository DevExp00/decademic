package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.LessonDto;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.entities.Module;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public LessonDto entityToDto(Lesson lesson){
        if(Objects.isNull(lesson))throw new IllegalArgumentException("Lesson can not be null");
        LessonDto lessonDto = new LessonDto();
        lessonDto.setTitle(lesson.getTitle() == null ? null : lesson.getTitle());
        lessonDto.setDescription(lesson.getDescription() == null ? null : lesson.getDescription());
        lessonDto.setContent(lesson.getContent()== null ? null : lesson.getContent());
        lessonDto.setContentType(lesson.getContentType() == null ? null : lesson.getContentType());
        lessonDto.setId(lesson.getId());
        lessonDto.setOrder(lesson.getOrder() == null ? null : lesson.getOrder());
        lessonDto.setPointsToPass(lesson.getPointsToPass() == null ? null : lesson.getPointsToPass());
        return lessonDto;
    }

    public List<LessonDto> entityToDto(List<Lesson> lessons){
        List<LessonDto> lessonDtos = new ArrayList<>();
        for(Lesson lesson: lessons){
            LessonDto lessonDto = new LessonDto();
            lessonDto.setTitle(lesson.getTitle() == null ? null : lesson.getTitle());
            lessonDto.setDescription(lesson.getDescription() == null ? null : lesson.getDescription());
            lessonDto.setContent(lesson.getContent()== null ? null : lesson.getContent());
            lessonDto.setContentType(lesson.getContentType() == null ? null : lesson.getContentType());
            lessonDto.setId(lesson.getId());
            lessonDto.setOrder(lesson.getOrder() == null ? null : lesson.getOrder());
            lessonDto.setPointsToPass(lesson.getPointsToPass() == null ? null : lesson.getPointsToPass());
            lessonDtos.add(lessonDto);
        }
        return lessonDtos;
    }
}
