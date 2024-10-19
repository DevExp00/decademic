package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.dtos.CourseCompletionDto;
import kz.djdegens.academic.entities.CourseCompletion;

public interface CourseCompletionData {

    CourseCompletion save(CourseCompletion courseCompletion);

    CourseCompletion findById(Long courseCompletionId);
}
