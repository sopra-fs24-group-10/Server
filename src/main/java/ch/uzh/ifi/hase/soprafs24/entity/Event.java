package ch.uzh.ifi.hase.soprafs24.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "EVENTS")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String description;
    private LocalDate createddate = LocalDate.now();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity host;

    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private Set<UserEntity> participants = new HashSet<>();

    // To check if a User is Host of Event
    private Boolean isHost(UserEntity userEntity) {
        return this.host.equals(userEntity);
    }
}