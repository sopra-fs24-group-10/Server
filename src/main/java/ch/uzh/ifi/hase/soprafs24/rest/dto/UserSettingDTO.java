package ch.uzh.ifi.hase.soprafs24.rest.dto;

import ch.uzh.ifi.hase.soprafs24.constant.SD;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSettingDTO {

    private SD.Setting design;
    
    private SD.Setting view;
}
