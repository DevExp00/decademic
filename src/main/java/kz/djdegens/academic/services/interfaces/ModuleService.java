package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.ModuleDto;
import kz.djdegens.academic.entities.LessonCompletion;

public interface ModuleService {

    ApplicationDto addModule(ModuleDto moduleDto);

    ApplicationDto getModule(Long moduleId);

    void startAttempt(Long moduleId, ApplicationDto applicationDto);

    void handleModuleCompletion(LessonCompletion lessonCompletion);
}
