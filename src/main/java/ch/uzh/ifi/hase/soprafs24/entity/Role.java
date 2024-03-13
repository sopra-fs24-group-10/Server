package ch.uzh.ifi.hase.soprafs24.entity;
import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "ROLES")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}