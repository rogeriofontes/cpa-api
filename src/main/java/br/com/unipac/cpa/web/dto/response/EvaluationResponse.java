package br.com.unipac.cpa.web.dto.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data public class EvaluationResponse {

	@Getter @Setter private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String description;
	@Getter @Setter private String date;
}
