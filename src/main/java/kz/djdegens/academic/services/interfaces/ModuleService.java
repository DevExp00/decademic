package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.ModuleDto;

public interface ModuleService {

    void addModule(ModuleDto moduleDto);

    ApplicationDto getModule(Long moduleId);
}
