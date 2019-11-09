package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.repository.CourseRepository;
import br.com.unipac.cpa.model.service.CourseService;
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
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course edit(Long id, Course course) {
		if (id != null && course != null) {
			Optional<Course> result = findById(id);
			if (result.isPresent()) {
				course.setId(id);
				return courseRepository.save(course);
			} else {
				throw new ResourceNotFoundException("ClientTypee não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<Course> findById(Long id) {
		return courseRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.COURSES_IN_CACHE)
	public List<Course> listAll() {
		return courseRepository.findAll();
	}

	@Override
	@Cacheable(Constants.COURSES_IN_CACHE)
	public Page<Course> findAllPageable(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Course> result = courseRepository.findById(id);

		if (result.isPresent()) {
			courseRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Optional<Course> findByName(String name) {
		return courseRepository.findByName(name);
	}
}
