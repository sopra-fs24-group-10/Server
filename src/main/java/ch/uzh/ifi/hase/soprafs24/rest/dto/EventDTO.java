package ch.uzh.ifi.hase.soprafs24.rest.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private LocalDate createddate;
}
