package br.com.unipac.cpa.web.dto.request;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ProfessorDisciplineRequest implements Serializable {
    private static final long serialVersionUID = 1968617978307583893L;

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long disciplineId;
    @Getter
    @Setter
    private Long professorId;

}
