package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.LikertSkala;
import br.com.unipac.cpa.model.repository.LikertSkalaRepository;
import br.com.unipac.cpa.model.service.LikertSkalaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LikertSkalaServiceImpl implements LikertSkalaService {


	@Autowired
	private LikertSkalaRepository likertSkalaRepository;

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
		return likertSkalaRepository.findAll();
	}

	@Override
	@Cacheable(Constants.LIKERT_SKALA_IN_CACHE)
	public Page<LikertSkala> findAllPageable(Pageable pageable) {
		return likertSkalaRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<LikertSkala> result = findById(id);

		if (result.isPresent()) {
			likertSkalaRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
