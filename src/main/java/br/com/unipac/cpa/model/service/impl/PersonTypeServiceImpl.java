package br.com.unipac.cpa.model.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.service.PersonTypeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonTypeServiceImpl implements PersonTypeService {

	@Override
	@Cacheable(Constants.PERSON_TYPES_IN_CACHE)
	public Optional<List<PersonType>> all() {
		List<PersonType> types = PersonType.getPersonTypes();
		return Optional.of(types);
	}

}
