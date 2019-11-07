package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "professor_discipline")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "discipline", "professor" })
@Builder
public class ProfessorDiscipline extends AudityEntity  {

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    @Getter
    @Setter
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @Getter
    @Setter
    private Professor professor;
}
