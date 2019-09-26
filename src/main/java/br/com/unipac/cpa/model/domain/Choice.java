
package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.awt.*;

@Entity
@Table(name = "choice")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "description", "professor" })
@Builder
public class Choice extends AudityEntity {

    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String point;

    public void update(Long id, Choice choice) {
        super.setId(id);
        this.description = choice.getDescription();
        this.point = choice.getPoint();
    }
}