package kz.djdegens.academic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dc_quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "points_to_pass")
    private Integer pointsToPass;

    @Column(name = "order_by")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

}
