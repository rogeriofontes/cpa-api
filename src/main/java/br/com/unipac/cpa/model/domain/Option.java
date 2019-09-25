
package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "option")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "description", "professor" })
@Builder
public class Option extends AudityEntity {

    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String point;

    public void update(Long id, Option option) {
        super.setId(id);
        this.description = option.getDescription();
        this.point = option.getPoint();
    }
}