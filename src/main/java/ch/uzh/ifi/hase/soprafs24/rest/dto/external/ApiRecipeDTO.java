package ch.uzh.ifi.hase.soprafs24.rest.dto.external;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * ApiIngredientDTO is structured specifically to match the JSON response format
 * of the external API.
 * This API returns ingredient information in a nested structure, which
 * necessitates the use of
 * nested static classes within this DTO. The design of this class facilitates
 * automatic
 * deserialization of the JSON response into Java objects, allowing for
 * straightforward
 * processing and conversion to domain entities.
 *
 * The structure of ApiIngredientDTO and its nested classes directly correspond
 * to the JSON
 * fields returned by the API, ensuring that Spring's RestTemplate can
 * seamlessly bind the
 * response data to these objects. This approach significantly simplifies the
 * handling of
 * external API data within our application.
 */

@Getter
@Setter
public class ApiRecipeDTO {

    private Long id;
    private String title;
    private String image;
    private int servings;
    private Set<Ingredient> extendedIngredients;

    @Getter
    @Setter
    public static class Ingredient {
        private Long id;
        private String name;
        private String image;
        private Double amount;
        private String unit;
        // Additional fields as needed based on the JSON structure
    }
}
