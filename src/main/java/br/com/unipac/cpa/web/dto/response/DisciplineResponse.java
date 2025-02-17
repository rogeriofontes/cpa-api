package br.com.unipac.cpa.web.dto.response;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.domain.Professor;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class DisciplineResponse implements Serializable {

    @Getter
    @Setter
    private Long id;

    @NotNull
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Long periodId;
}
