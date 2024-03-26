package ch.uzh.ifi.hase.soprafs24.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ch.uzh.ifi.hase.soprafs24.entity.Ingredient;
import ch.uzh.ifi.hase.soprafs24.rest.dto.external.ApiRecipeDTO;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExternalApiService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final RestTemplate restTemplate; // Bean created in Application.java

    // Split into Recipe get and Ingredient get and perform assignment setup
    // Add auth here via request header as x-api-key
    public Set<Ingredient> fetchRecipeInformation(Long recipeId) {
        String url = "https://api.spoonacular.com/recipes/" + recipeId + "/information";
        ApiRecipeDTO apiRecipeDTO = restTemplate.getForObject(url, ApiRecipeDTO.class);

        return apiRecipeDTO.getExtendedIngredients().stream()
                .map(ingredient -> DTOMapper.INSTANCE.convertApiRecipeDTOIngredientToIngredient(ingredient))
                .collect(Collectors.toSet());
    }
}
