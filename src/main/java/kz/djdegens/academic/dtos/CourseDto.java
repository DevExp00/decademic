package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {

    private Long id;
    private String title;
    private String description;
    private Long creatorId;
    private String pointsToPass;
    private Integer price;
    private Integer rate;
    private Integer countModules;
    private Integer followers;
    private Boolean isActive;

    private List<ModuleDto> modules;

}
