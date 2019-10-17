package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.*;
import br.com.unipac.cpa.model.repository.CourseRepository;
import br.com.unipac.cpa.model.repository.DisciplineRepository;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
import br.com.unipac.cpa.model.repository.StudentRepository;
import br.com.unipac.cpa.web.dto.request.StudentDisciplineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentDisciplineRequestConverter implements Converter<StudentDisciplineRequest, StudentDiscipline> {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public StudentDiscipline convert(StudentDisciplineRequest studentDisciplineRequest) {
        Optional<Discipline> discipline = Optional.empty();
        Optional<Student> student = Optional.empty();

        StudentDiscipline studentDiscipline = new StudentDiscipline();

        if (studentDisciplineRequest.getStudentId() != null && studentDisciplineRequest.getStudentId().intValue() > 0) {
            student = studentRepository.findById(studentDisciplineRequest.getStudentId());
            if (student.isPresent()) {
                studentDiscipline.setStudent(student.get());
            }
        }

        if (studentDisciplineRequest.getDisciplineId() != null && studentDisciplineRequest.getDisciplineId().intValue() > 0) {
            discipline = disciplineRepository.findById(studentDisciplineRequest.getDisciplineId());
            if (discipline.isPresent()) {
                studentDiscipline.setDiscipline(discipline.get());
            }
        }

        return studentDiscipline;
    }
}
