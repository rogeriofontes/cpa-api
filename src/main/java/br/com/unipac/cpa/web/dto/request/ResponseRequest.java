package br.com.unipac.cpa.web.dto.request;

import br.com.unipac.cpa.model.domain.*;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ResponseRequest implements Serializable {
    private static final long serialVersionUID = 1968617978307583893L;

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Long questionId;
    @Getter
    @Setter
    private Long choiceId;
    @Getter
    @Setter
    private Long professorDisciplineId;
    @Getter
    @Setter
    private Long studentDisciplineId;
}
