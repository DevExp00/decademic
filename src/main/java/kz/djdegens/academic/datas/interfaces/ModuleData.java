package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.Module;

public interface ModuleData {

    Module save(Module module);

    Module findById(Long moduleId);
}
