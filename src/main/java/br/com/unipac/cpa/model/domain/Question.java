package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "description", "date" })
@Builder
public class Question extends AudityEntity {

    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    @Getter
    @Setter
    private Evaluation evaluation;

    //Delegate
    public void update(Long id, Question question) {
       super.setId(id);
       this.title = question.getTitle();
       this.description = question.getDescription();
       this.evaluation = question.getEvaluation();
    }

}
