package ch.uzh.ifi.hase.soprafs24.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "INGREDIENTS")
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long api_id;
    
    private String name;
    private String description;
    private String unit;
    private String image;
}