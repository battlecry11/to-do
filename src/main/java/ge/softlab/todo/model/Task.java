package ge.softlab.todo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String task;

}
