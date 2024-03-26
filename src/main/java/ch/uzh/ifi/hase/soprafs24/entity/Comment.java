package ch.uzh.ifi.hase.soprafs24.entity;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "COMMENTS")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    private LocalDate createddate = LocalDate.now();
}