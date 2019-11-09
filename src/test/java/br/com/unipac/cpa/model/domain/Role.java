package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "role" })
public class Role extends AudityEntity {

	private static final long serialVersionUID = -2719226491222823385L;

	@Getter
	@Setter
	@Column(name = "role")
	private String role;

}