package kz.djdegens.academic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "order")
    private Integer order;

    @Column(name = "points_to_pass")
    private Integer pointsToPass;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
