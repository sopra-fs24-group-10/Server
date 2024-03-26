package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavouriteDTO {
    private Long id;
    private Long api_id;
    private String name;
    private String description;
    private String image;
}
