package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "company_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name" })
public class CompanyType extends AudityEntity {
	private static final long serialVersionUID = -2647716282886497886L;

	@NotNull
	@Getter
	@Setter
	private String name;

	public void updade(Long id, CompanyType clientType) {
		super.setId(id);
		this.name = clientType.getName();
	}

}
