package br.com.unipac.cpa.web.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "description", "point" })
@Builder
public class ChoiceRequest {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String point;
}