package kz.djdegens.academic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dc_lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 5000)
    private String content;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "order_by")
    private Integer order;

    @Column(name = "points_to_pass")
    private Integer pointsToPass;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
