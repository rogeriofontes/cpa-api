package br.com.unipac.cpa.web.dto.response;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class StudentDisciplineResponse implements Serializable {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long disciplineId;
    @Getter
    @Setter
    private Long studentId;
}
