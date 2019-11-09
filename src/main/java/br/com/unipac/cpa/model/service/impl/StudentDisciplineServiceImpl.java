package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.StudentDiscipline;
import br.com.unipac.cpa.model.repository.StudentDisciplineRepository;
import br.com.unipac.cpa.model.service.StudentDisciplineService;
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
public class StudentDisciplineServiceImpl implements StudentDisciplineService {

	@Autowired
	private StudentDisciplineRepository studentDisciplineRepository;

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
	@Cacheable(Constants.STUDENTS_DISCIPLINE_IN_CACHE)
	public List<StudentDiscipline> listAll() {
		return studentDisciplineRepository.findAll();
	}

	@Override
	@Cacheable(Constants.STUDENTS_DISCIPLINE_IN_CACHE)
	public Page<StudentDiscipline> findAllPageable(Pageable pageable) {
		return studentDisciplineRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<StudentDiscipline> result = findById(id);

		if (result.isPresent()) {
			studentDisciplineRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
