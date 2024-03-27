package ch.uzh.ifi.hase.soprafs24.entity;

import java.util.HashSet;
import java.util.Set;

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
    private int servings;
    private String image;
    private float rating;
    private int rating_count;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "instruction_id", referencedColumnName = "id")
    private Set<Instruction> instructions = new HashSet<>();

}