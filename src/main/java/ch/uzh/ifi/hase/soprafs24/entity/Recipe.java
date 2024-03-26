package ch.uzh.ifi.hase.soprafs24.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "RECIPES")
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private Long api_id;
    
    private String name;
    private String description;
    private String instructions;
    private int servings;
    private String image;
    private float rating;
    private int rating_count;
}