package br.com.unipac.cpa.model.domain;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class AudityEntity implements Serializable {

	private static final long serialVersionUID = -8812000052333532897L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@NotNull
	@Column(name = "created_date", nullable = false)
	@JsonIgnore
	@CreatedDate
	@Getter
	@Setter
	private transient LocalDateTime createdDate = DateUtil.convert(new Date());

	@NotNull
	@Column(name = "create_by")
	@JsonIgnore
	@CreatedBy
	@Getter
	@Setter
	private String createBy = Constants.CURRENT_USER;

	@Column(name = "last_modified_date")
	@JsonIgnore
	@LastModifiedDate
	@Getter
	@Setter
	private transient LocalDateTime lastModifiedDate;

	@Column(name = "last_modified_by")
	@JsonIgnore
	@LastModifiedBy
	@Getter
	@Setter
	private String lastModifiedBy;

}
