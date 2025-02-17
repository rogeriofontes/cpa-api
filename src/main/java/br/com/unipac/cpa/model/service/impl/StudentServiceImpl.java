package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Student;
import br.com.unipac.cpa.model.repository.StudentRepository;
import br.com.unipac.cpa.model.service.StudentService;
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
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public boolean sendInformation(Student student) {
		log.info("Saved: " + !StringUtils.isEmpty(student.toString()));
		return false;
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student edit(Long id, Student student) {
		if (id != null && student != null) {
			Optional<Student> result = findById(id);

			if (result.isPresent()) {
				student.setId(id);
				return studentRepository.save(student);
			} else {
				throw new ResourceNotFoundException("EmployeeTypee não existe");
			}
		}
		return null;
	}

	@Override
	@Cacheable(Constants.STUDENTS_IN_CACHE)
	public Optional<Student> findById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.STUDENTS_IN_CACHE)
	public List<Student> listAll() {
		Iterable<Student> itr = studentRepository.findAll();
		return (List<Student>) itr;
	}

	@Override
	@Cacheable(Constants.STUDENTS_IN_CACHE)
	public Page<Student> findAllPageable(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Student> result = findById(id);

		if (result != null) {
			studentRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Optional<Student> findByName(String name) {
		return studentRepository.findByName(name);
	}

}
