package br.com.unipac.cpa.web.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PeriodRequest implements Serializable {
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
    private int representationNumber;

    @Getter
    @Setter
    private Long courseId;

}
