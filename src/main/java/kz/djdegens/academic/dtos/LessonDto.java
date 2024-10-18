package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonDto {

    private Long moduleId;

    private String title;

    private String description;

    private String contentType;

    private String content;

    private Integer pointsToPass;

    private Integer order;
}
