package ch.uzh.ifi.hase.soprafs24.rest.dto.external;

import java.util.List;

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
public class ApiInstructionDTO {

    private List<Instruction> steps;

    @Getter
    @Setter
    public static class Instruction {
        private Long number;
        private String step;
    }
}