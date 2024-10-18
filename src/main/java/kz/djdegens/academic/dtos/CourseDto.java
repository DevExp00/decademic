package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto {

    private String title;
    private String description;
    private Long creatorId;
    private String pointsToPass;
}
