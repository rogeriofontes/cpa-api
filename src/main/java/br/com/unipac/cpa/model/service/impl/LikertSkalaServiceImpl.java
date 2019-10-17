package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.LikertSkala;
import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.repository.LikertSkalaRepository;
import br.com.unipac.cpa.model.repository.PeriodRepository;
import br.com.unipac.cpa.model.service.LikertSkalaService;
import br.com.unipac.cpa.model.service.PeriodService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikertSkalaServiceImpl implements LikertSkalaService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LikertSkalaRepository likertSkalaRepository;
	private Iterable<LikertSkala> itr;

	@Override
	public LikertSkala save(LikertSkala e) {
		return likertSkalaRepository.save(e);
	}

	@Override
	public LikertSkala edit(Long id, LikertSkala likertSkala) {
		if (id != null && likertSkala != null) {
			Optional<LikertSkala> result = findById(id);

			if (result.isPresent()) {
				likertSkala.setId(id);
				return likertSkalaRepository.save(likertSkala);
			} else {
				throw new ResourceNotFoundException("likertSkala não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<LikertSkala> findById(Long id) {
		return likertSkalaRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.LIKERT_SKALA_IN_CACHE)
	public List<LikertSkala> listAll() {
		Iterable<LikertSkala> itr = likertSkalaRepository.findAll();
		return (List<LikertSkala>) itr;
	}

	@Override
	@Cacheable(Constants.LIKERT_SKALA_IN_CACHE)
	public Page<LikertSkala> findAllPageable(Pageable pageable) {
		return likertSkalaRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<LikertSkala> result = findById(id);

		if (result != null) {
			likertSkalaRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
