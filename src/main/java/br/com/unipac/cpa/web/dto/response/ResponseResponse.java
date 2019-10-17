package br.com.unipac.cpa.web.dto.response;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ResponseResponse implements Serializable {

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
    private Long studentId;
    @Getter
    @Setter
    private Long professorDisciplineId;
    @Getter
    @Setter
    private Long studentDisciplineId;
}
