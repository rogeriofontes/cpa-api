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
public class LikertSkalaResponse implements Serializable {

    @Getter
    @Setter
    private Long id;

    @NotNull
    @Getter
    @Setter
    private String name;
}
