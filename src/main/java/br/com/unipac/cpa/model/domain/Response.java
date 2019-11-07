package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "response")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "title", "description", "question", "choice", "student", "professorDiscipline", "studentDiscipline" })
@Builder
public class Response extends AudityEntity {

    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @Getter
    @Setter
    private Question question;
    @ManyToOne
    @JoinColumn(name = "choice_id")
    @Getter
    @Setter
    private Choice choice;
    @ManyToOne
    @JoinColumn(name = "professor_discipline_id")
    @Getter
    @Setter
    private ProfessorDiscipline professorDiscipline;

    @ManyToOne
    @JoinColumn(name = "student_discipline_id")
    @Getter
    @Setter
    private StudentDiscipline studentDiscipline;

    //Delegate
    public void update(Long id, Response response) {
       super.setId(id);
       this.title = response.getTitle();
       this.description = response.getDescription();
       this.question = response.getQuestion();
       this.choice = response.getChoice();
       this.student = response.getStudent();
       this.studentDiscipline = response.getStudentDiscipline();
       this.professorDiscipline = response.getProfessorDiscipline();
    }

}
