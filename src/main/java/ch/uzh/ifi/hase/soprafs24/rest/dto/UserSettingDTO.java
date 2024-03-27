package ch.uzh.ifi.hase.soprafs24.rest.dto;

import javax.validation.constraints.NotNull;
import ch.uzh.ifi.hase.soprafs24.constant.SD;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSettingDTO {

    @NotNull(message = "Design is required!")
    private SD.Setting design;
    
    @NotNull(message = "View is required!")
    private SD.Setting view;
}
