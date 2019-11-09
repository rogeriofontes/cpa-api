package br.com.unipac.cpa.web.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data public class EvaluationRequest {

	@Getter @Setter private String name;
	@Getter @Setter private String description;
	@Getter @Setter private String date;

}
