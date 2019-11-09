package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Student;
import br.com.unipac.cpa.model.service.StudentService;
import br.com.unipac.cpa.web.dto.request.StudentRequest;
import br.com.unipac.cpa.web.dto.response.StudentResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentSupport {

	private final Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private StudentService studentService;

	@Autowired
	private ConversionService conversionService;
	
	public StudentResponse convertToFindById(Long id) {
		StudentResponse founded = null;
		Optional<Student> student = studentService.findById(id);

		if (student.isPresent()) {
			founded = conversionService.convert(student.get(), StudentResponse.class);
			if (founded != null)
				log.info("Company: {} ", founded);
		} else {
			throw new ResourceNotFoundException("Student not found");
		}
		return founded;
	}

	public StudentResponse convertToFindByName(String name) {
		StudentResponse founded = null;
		Optional<Student> student = studentService.findByName(name);

		if (student.isPresent()) {
			founded = conversionService.convert(student.get(), StudentResponse.class);
			if (founded != null)
				log.info("Company: {} ", founded);
		} else {
			throw new ResourceNotFoundException("Student not found");
		}

		return founded;
	}

	public List<StudentResponse> list() {
		List<StudentResponse> students = new ArrayList<>();
		studentService.listAll().forEach(student -> {
			StudentResponse saved = conversionService.convert(student, StudentResponse.class);
			students.add(saved);
		});
		return students;
	}

	public StudentResponse convertToCreate(StudentRequest studentRequest) {
		Student student = conversionService.convert(studentRequest, Student.class);
		Student saved = studentService.save(student);
		return getConverter(saved);
	}

	private StudentResponse getConverter(Student student) {
		return conversionService.convert(student, StudentResponse.class);
	}

	public StudentResponse convertToChange(Long id, StudentRequest studentRequest) {
		Student student = conversionService.convert(studentRequest, Student.class);
		Student updated = studentService.edit(id, student);
		return getConverter(updated);
	}

	public boolean remove(Long id) {
		return studentService.remove(id);
	}
}
