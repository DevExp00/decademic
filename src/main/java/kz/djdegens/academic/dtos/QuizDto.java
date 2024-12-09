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
public class QuizDto {

    private Long id;

    private String title;

    private String pointsToPass;

    private Integer order;

    private Long lessonId;

    private List<QuestionDto> questions;
    private Boolean isActive;

}
