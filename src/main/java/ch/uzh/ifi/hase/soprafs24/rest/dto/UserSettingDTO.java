package ch.uzh.ifi.hase.soprafs24.rest.dto;

import javax.validation.constraints.NotBlank;

import ch.uzh.ifi.hase.soprafs24.constant.SD;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSettingDTO {

    @NotBlank(message = "Design is required!")
    private SD.Setting design;
    
    @NotBlank(message = "View is required!")
    private SD.Setting view;
}
