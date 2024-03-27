package ch.uzh.ifi.hase.soprafs24.rest.dto;

import java.util.Set;

import ch.uzh.ifi.hase.soprafs24.entity.Ingredient;
import ch.uzh.ifi.hase.soprafs24.entity.Instruction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDTO {
    
    private Long id;
    private Long api_id;
    private String name;
    private Set<Instruction> instructions;
    private int servings;
    private String image;
    private float rating;
    private int rating_count;
}
