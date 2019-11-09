package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.repository.DisciplineRepository;
import br.com.unipac.cpa.model.service.DisciplineService;
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
public class DisciplineServiceImpl implements DisciplineService {

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Override
	public boolean sendInformation(Discipline discipline) {
		return false;
	}

	@Override
	public Discipline save(Discipline e) {
		return disciplineRepository.save(e);
	}

	@Override
	public Discipline edit(Long id, Discipline discipline) {
		if (id != null && discipline != null) {
			Optional<Discipline> result = findById(id);

			if (result.isPresent()) {
				discipline.setId(id);
				return disciplineRepository.save(discipline);
			} else {
				throw new ResourceNotFoundException("Disciplina não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<Discipline> findById(Long id) {
		return disciplineRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.DISCIPLINES_IN_CACHE)
	public List<Discipline> listAll() {
		Iterable<Discipline> itr = disciplineRepository.findAll();
		return (List<Discipline>) itr;
	}

	@Override
	@Cacheable(Constants.DISCIPLINES_IN_CACHE)
	public Page<Discipline> findAllPageable(Pageable pageable) {
		return disciplineRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Discipline> result = findById(id);

		if (result.isPresent()) {
			disciplineRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Optional<Discipline> findByName(String name) {
		return disciplineRepository.findByName(name);
	}

}
