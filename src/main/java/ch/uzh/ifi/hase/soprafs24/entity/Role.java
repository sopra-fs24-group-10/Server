package ch.uzh.ifi.hase.soprafs24.entity;
import javax.persistence.*;

import ch.uzh.ifi.hase.soprafs24.constant.SD;
import lombok.Data;

@Entity
@Table(name = "ROLES")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SD.UserRole name;
}