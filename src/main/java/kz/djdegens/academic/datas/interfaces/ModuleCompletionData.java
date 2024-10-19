package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.dtos.ModuleCompletionDto;
import kz.djdegens.academic.entities.ModuleCompletion;

public interface ModuleCompletionData {

    ModuleCompletion save(ModuleCompletion moduleCompletion);

    ModuleCompletion findById(Long moduleCompletionId);
}
