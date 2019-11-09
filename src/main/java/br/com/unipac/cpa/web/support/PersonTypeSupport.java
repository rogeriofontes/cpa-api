package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.service.PersonTypeService;
import br.com.unipac.cpa.web.dto.response.PersonTypeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class PersonTypeSupport {

	@Autowired
	private PersonTypeService service;

	@Autowired
	private ConversionService documentRegionConvert;

	public List<PersonTypeResponse> list() {
		List<PersonTypeResponse> documentRegions = new ArrayList<>();
		Optional<List<PersonType>> list = service.all();
		
		if (list.isPresent()) {
			list.get().forEach(documentRegion -> {
				PersonTypeResponse saved = documentRegionConvert.convert(documentRegion, PersonTypeResponse.class);
				documentRegions.add(saved);
			});

			return documentRegions;
		}
		
		return Collections.emptyList();
	}
}
