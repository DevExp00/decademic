package kz.djdegens.academic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dc_quiz_completion")
public class QuizCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "score")
    private Integer score = 0;

    @Column(name = "is_passed")
    private Boolean isPassed = false;

    @Column(name = "completed_at")
    private Date completedAt;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "lesson_completion_id")
    private LessonCompletion lessonCompletion;
}
