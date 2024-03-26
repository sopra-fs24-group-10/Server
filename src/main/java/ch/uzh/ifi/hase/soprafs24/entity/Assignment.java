package ch.uzh.ifi.hase.soprafs24.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ASSIGNMENTS")
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private double quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;
}