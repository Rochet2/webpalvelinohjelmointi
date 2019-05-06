/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtables;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author rimi
 */
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Student extends AbstractPersistable<Long> {
    String firstName;
    String lastName;
    @ManyToMany(mappedBy = "students")
    List<Course> courses = new ArrayList<>();
}
