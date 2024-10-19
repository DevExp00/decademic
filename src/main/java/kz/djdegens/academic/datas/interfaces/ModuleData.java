package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.entities.Module;

import java.util.List;

public interface ModuleData {

    Module save(Module module);

    Module findById(Long moduleId);

    List<Module> findAllByCourseId(Long courseId);

}
