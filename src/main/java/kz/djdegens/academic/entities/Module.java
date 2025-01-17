package kz.djdegens.academic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dc_module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "order_by")
    private Integer order;

    @Column(name = "points_to_pass")
    private Integer pointsToPass;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
