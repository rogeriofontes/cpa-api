package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.StudentDiscipline;
import br.com.unipac.cpa.model.service.StudentDisciplineService;
import br.com.unipac.cpa.web.dto.request.StudentDisciplineRequest;
import br.com.unipac.cpa.web.dto.response.StudentDisciplineResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class StudentDisciplineSupport {

    @Autowired
    private StudentDisciplineService service;

    @Autowired
    private ConversionService conversion;

    public StudentDisciplineResponse convertToFindById(Long id) {
        StudentDisciplineResponse founded = null;
        Optional<StudentDiscipline> studentDiscipline = service.findById(id);

        if (studentDiscipline.isPresent()) {
            founded = conversion.convert(studentDiscipline.get(), StudentDisciplineResponse.class);
            log.info("StudentDiscipline: " + founded.toString());
        } else {
            throw new ResourceNotFoundException("Company Type not found");
        }

        return founded;
    }

    public List<StudentDisciplineResponse> list() {
        List<StudentDisciplineResponse> studentDisciplines = new ArrayList<>();
        service.listAll().forEach(studentDiscipline -> {
            StudentDisciplineResponse saved = conversion.convert(studentDiscipline, StudentDisciplineResponse.class);
            studentDisciplines.add(saved);
        });
        return studentDisciplines;
    }

    public StudentDisciplineResponse convertToCreate(StudentDisciplineRequest studentDisciplineRequest) {
        StudentDiscipline studentDiscipline = conversion.convert(studentDisciplineRequest, StudentDiscipline.class);
        StudentDiscipline result = service.save(studentDiscipline);
        return conversion.convert(result, StudentDisciplineResponse.class);
    }

    public StudentDisciplineResponse convertToChange(Long id, StudentDisciplineRequest studentDisciplineRequest) {
        StudentDiscipline studentDiscipline = conversion.convert(studentDisciplineRequest, StudentDiscipline.class);
        StudentDiscipline result = service.edit(id, studentDiscipline);
        return conversion.convert(result, StudentDisciplineResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
