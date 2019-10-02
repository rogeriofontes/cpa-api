package br.com.unipac.cpa.web.dto.request;

import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.domain.Student;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class DisciplineRequest implements Serializable {
    private static final long serialVersionUID = 1968617978307583893L;

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
    private Long professorId;

    @Getter
    @Setter
    private Set<Professor> professors;

    @Getter
    @Setter
    private Set<Student> students;

}
