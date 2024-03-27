package ch.uzh.ifi.hase.soprafs24.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ch.uzh.ifi.hase.soprafs24.entity.Ingredient;
import ch.uzh.ifi.hase.soprafs24.entity.Instruction;
import ch.uzh.ifi.hase.soprafs24.entity.Recipe;
import ch.uzh.ifi.hase.soprafs24.rest.dto.external.ApiInstructionDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.external.ApiRecipeDTO;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExternalApiService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final RestTemplate restTemplate; // Bean created in Application.java

    @Value("${SPOONACULAR_API_KEY}") //create env variable
    private String spoonacularApiKey;

    public Pair<Recipe, Set<Ingredient>> fetchRecipeInformation(Long recipeId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", spoonacularApiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://api.spoonacular.com/recipes/" + recipeId + "/information";
        ResponseEntity<ApiRecipeDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, ApiRecipeDTO.class);
        ApiRecipeDTO apiRecipeDTO = response.getBody();

        Recipe recipe = DTOMapper.INSTANCE.convertApiRecipeDTOToRecipe(apiRecipeDTO);
        recipe.setInstructions(fetchRecipeInstructions(recipeId)); // associate Recipe with Instructions

        Set<Ingredient> ingredients = apiRecipeDTO.getExtendedIngredients().stream()
                .map(ingredient -> DTOMapper.INSTANCE.convertApiRecipeDTOIngredientToIngredient(ingredient))
                .collect(Collectors.toSet());

        return Pair.of(recipe, ingredients);
    }

    public Set<Instruction> fetchRecipeInstructions(Long recipeId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", spoonacularApiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://api.spoonacular.com/recipes/" + recipeId + "/analyzedInstructions";
        ResponseEntity<List<ApiInstructionDTO>> response = restTemplate.exchange(
            url, 
            HttpMethod.GET, 
            entity, 
            new ParameterizedTypeReference<List<ApiInstructionDTO>>() {}
        );
        List<ApiInstructionDTO> instructionDTOs = response.getBody();

        return instructionDTOs.stream()
            .flatMap(instructionDTO -> instructionDTO.getSteps().stream())
            .map(instruction -> DTOMapper.INSTANCE.convertApiInstructionDTOToInstruction(instruction))
            .collect(Collectors.toSet());
    }
}
