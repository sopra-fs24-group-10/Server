package ch.uzh.ifi.hase.soprafs24.entity;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}