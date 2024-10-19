package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.ModuleCompletionData;
import kz.djdegens.academic.entities.ModuleCompletion;
import kz.djdegens.academic.repositories.ModuleCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ModuleCompletionDataImpl implements ModuleCompletionData {

    private final ModuleCompletionRepository moduleCompletionRepository;

    @Override
    public ModuleCompletion save(ModuleCompletion moduleCompletion) {
        if(Objects.isNull(moduleCompletion))throw new IllegalArgumentException("Module completion can not be null");
        return moduleCompletionRepository.save(moduleCompletion);
    }


    @Override
    public ModuleCompletion findById(Long moduleCompletionId) {
        if(Objects.isNull(moduleCompletionId))throw new IllegalArgumentException("Module completion id can not be null");
        Optional<ModuleCompletion> optionalModuleCompletion = moduleCompletionRepository.findById(moduleCompletionId);
        if(optionalModuleCompletion.isEmpty())throw new NoSuchElementException("Module completion not found");
        return optionalModuleCompletion.get();
    }
}
