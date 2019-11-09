package br.com.unipac.cpa.web.dto.request;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AccountRequest implements Serializable {
	private static final long serialVersionUID = -5623786798388768855L;
	
	@Getter @Setter private String email;
	@Getter @Setter private String password;
	@Getter @Setter private Long registerNumber;
	@Getter
	@Setter
	private String userType;
}
