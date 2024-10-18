package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleDto {

    private Long id;
    private String title;
    private String description;
    private Long courseId;
    private Integer pointsToPass;
    private Integer orderBy;

    private List<LessonDto> lessons;
}
