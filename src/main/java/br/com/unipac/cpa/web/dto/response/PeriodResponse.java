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
public class PeriodResponse implements Serializable {

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
