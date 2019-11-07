package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.ProfessorDiscipline;
import br.com.unipac.cpa.model.repository.ProfessorDisciplineRepository;
import br.com.unipac.cpa.model.service.ProfessorDisciplineService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class ProfessorDisciplineServiceImpl implements ProfessorDisciplineService {

	@Autowired
	private ProfessorDisciplineRepository professorDisciplineRepository;
	private Iterable<ProfessorDiscipline> itr;

	@Override
	public ProfessorDiscipline save(ProfessorDiscipline e) {
		return professorDisciplineRepository.save(e);
	}

	@Override
	public ProfessorDiscipline edit(Long id, ProfessorDiscipline professorDiscipline) {
		if (id != null && professorDiscipline != null) {
			Optional<ProfessorDiscipline> result = findById(id);

			if (result.isPresent()) {
				professorDiscipline.setId(id);
				return professorDisciplineRepository.save(professorDiscipline);
			} else {
				throw new ResourceNotFoundException("BusinessFrontTypee não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<ProfessorDiscipline> findById(Long id) {
		return professorDisciplineRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.PERIODS_IN_CACHE)
	public List<ProfessorDiscipline> listAll() {
		Iterable<ProfessorDiscipline> itr = professorDisciplineRepository.findAll();
		return (List<ProfessorDiscipline>) itr;
	}

	@Override
	@Cacheable(Constants.PERIODS_IN_CACHE)
	public Page<ProfessorDiscipline> findAllPageable(Pageable pageable) {
		return professorDisciplineRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<ProfessorDiscipline> result = findById(id);

		if (result != null) {
			professorDisciplineRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
