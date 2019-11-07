package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "likert_skala")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data public class LikertSkala extends AudityEntity {

    private static final long serialVersionUID = -3713207158221953195L;

    @NotNull
    @Getter
    @Setter
    private String name;

    public void update(LikertSkala period){
        this.name = period.getName();
    }
}
