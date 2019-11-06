package br.com.unipac.cpa.web.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "title", "description", "evaluationId" })
@Builder
public class QuestionResponse {

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
    private Long evaluationId;
}
