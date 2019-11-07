package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_discipline")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "discipline", "student" })
@Builder
public class StudentDiscipline extends AudityEntity  {

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    @Getter
    @Setter
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @Getter
    @Setter
    private Student student;
}
