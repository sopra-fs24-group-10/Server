package ch.uzh.ifi.hase.soprafs24.rest.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    private Long id;

    @NotBlank(message = "Eventname is required!")
    private String name;

    @NotBlank(message = "Location is required!")
    private String location;
    private String description;
    
    @NotNull(message = "Eventdate is required!")
    private LocalDate evenDate;
    private LocalDate createddate;
}
