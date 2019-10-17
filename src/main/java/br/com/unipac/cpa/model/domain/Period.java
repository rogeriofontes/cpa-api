package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "period")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "representationNumber", "course" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data public class Period extends AudityEntity {

    private static final long serialVersionUID = -3713207158221953195L;

    @NotNull
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Column(name = "representation_number")
    private int representationNumber;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public void update(Period period){
        this.name = period.getName();
        this.representationNumber = period.getRepresentationNumber();
        this.course = period.getCourse();
    }
}
