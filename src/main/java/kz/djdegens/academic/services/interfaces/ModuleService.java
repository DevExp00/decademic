package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.ModuleDto;
import kz.djdegens.academic.entities.LessonCompletion;

public interface ModuleService {

    void addModule(ModuleDto moduleDto);

    void editModule(Long moduleId, ModuleDto moduleDto);

    ApplicationDto getModule(Long moduleId);

    void startAttempt(Long moduleId, ApplicationDto applicationDto);

    void handleModuleCompletion(LessonCompletion lessonCompletion);
}
