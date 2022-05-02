package example.ToDoList.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class ToDo {
    @Id @GeneratedValue
    private Long id;

    public String sentence;

    public FinishStatus finishStatus; //FINISH, TODO
}
