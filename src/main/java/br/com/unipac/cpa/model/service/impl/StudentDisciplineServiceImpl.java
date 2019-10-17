package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.StudentDiscipline;
import br.com.unipac.cpa.model.repository.StudentDisciplineRepository;
import br.com.unipac.cpa.model.service.StudentDisciplineService;
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
public class StudentDisciplineServiceImpl implements StudentDisciplineService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentDisciplineRepository studentDisciplineRepository;
	private Iterable<StudentDiscipline> itr;

	@Override
	public StudentDiscipline save(StudentDiscipline e) {
		return studentDisciplineRepository.save(e);
	}

	@Override
	public StudentDiscipline edit(Long id, StudentDiscipline studentDiscipline) {
		if (id != null && studentDiscipline != null) {
			Optional<StudentDiscipline> result = findById(id);

			if (result.isPresent()) {
				studentDiscipline.setId(id);
				return studentDisciplineRepository.save(studentDiscipline);
			} else {
				throw new ResourceNotFoundException("BusinessFrontTypee não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<StudentDiscipline> findById(Long id) {
		return studentDisciplineRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.PERIODS_IN_CACHE)
	public List<StudentDiscipline> listAll() {
		Iterable<StudentDiscipline> itr = studentDisciplineRepository.findAll();
		return (List<StudentDiscipline>) itr;
	}

	@Override
	@Cacheable(Constants.PERIODS_IN_CACHE)
	public Page<StudentDiscipline> findAllPageable(Pageable pageable) {
		return studentDisciplineRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<StudentDiscipline> result = findById(id);

		if (result != null) {
			studentDisciplineRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
