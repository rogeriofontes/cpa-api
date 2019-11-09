package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.repository.PeriodRepository;
import br.com.unipac.cpa.model.service.PeriodService;
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
public class PeriodServiceImpl implements PeriodService {

	@Autowired
	private PeriodRepository periodRepository;

	@Override
	public boolean sendInformation(Period period) {
		return false;
	}

	@Override
	public Period save(Period e) {
		return periodRepository.save(e);
	}

	@Override
	public Period edit(Long id, Period period) {
		if (id != null && period != null) {
			Optional<Period> result = findById(id);

			if (result.isPresent()) {
				period.setId(id);
				return periodRepository.save(period);
			} else {
				throw new ResourceNotFoundException("BusinessFrontTypee não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<Period> findById(Long id) {
		return periodRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.PERIODS_IN_CACHE)
	public List<Period> listAll() {
		return periodRepository.findAll();
	}

	@Override
	@Cacheable(Constants.PERIODS_IN_CACHE)
	public Page<Period> findAllPageable(Pageable pageable) {
		return periodRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Period> result = findById(id);

		if (result.isPresent()) {
			periodRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Optional<Period> findByName(String name) {
		return periodRepository.findByName(name);
	}

}
