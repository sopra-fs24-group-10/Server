package ch.uzh.ifi.hase.soprafs24.rest.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDTO {

    @NotNull(message = "Recipe id is required!")
    private Long id;
    private Long api_id;
    private String name;
    private int servings;
    private String image;
    private float rating;
    private int rating_count;
    private Set<InstructionDTO> instructions;
}
