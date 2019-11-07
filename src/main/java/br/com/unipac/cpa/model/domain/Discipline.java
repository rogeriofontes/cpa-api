package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "discipline")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "description", "course" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data public class Discipline extends AudityEntity {

    private static final long serialVersionUID = -3713207158221953195L;

    @NotNull
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

   /* @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "professor_discipline",
            joinColumns = @JoinColumn(name = "id_discipline", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_professor", referencedColumnName = "id"))
    private Set<Professor> professors;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "student_discipline",
            joinColumns = @JoinColumn(name = "id_discipline", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_student", referencedColumnName = "id"))
    private Set<Student> students;*/

    public void update(Discipline discipline){
        this.name = discipline.getName();
        this.description = discipline.getDescription();
        this.period = discipline.getPeriod();
    }
}
