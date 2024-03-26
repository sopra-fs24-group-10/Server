package ch.uzh.ifi.hase.soprafs24.entity;

import javax.persistence.*;

import ch.uzh.ifi.hase.soprafs24.constant.SD;
import lombok.Data;

@Entity
@Table(name = "SETTINGS")
@Data
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SD.Setting design;

    @Enumerated(EnumType.STRING)
    private SD.Setting view;
}