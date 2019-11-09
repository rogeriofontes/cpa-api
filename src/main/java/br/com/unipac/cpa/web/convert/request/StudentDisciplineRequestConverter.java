package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.domain.Student;
import br.com.unipac.cpa.model.domain.StudentDiscipline;
import br.com.unipac.cpa.model.repository.DisciplineRepository;
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
        StudentDiscipline studentDiscipline = new StudentDiscipline();
        studentDiscipline.setDiscipline(getDiscipline(studentDisciplineRequest));
        studentDiscipline.setStudent(getStudent(studentDisciplineRequest));

        return studentDiscipline;
    }

    private Discipline getDiscipline(StudentDisciplineRequest studentDisciplineRequest) {
        if (studentDisciplineRequest.getDisciplineId() != null && studentDisciplineRequest.getDisciplineId().intValue() > 0) {
            Optional<Discipline> discipline = disciplineRepository.findById(studentDisciplineRequest.getDisciplineId());
            if (discipline.isPresent()) {
               return discipline.get();
            }
        }

        return null;
    }

    private Student getStudent(StudentDisciplineRequest studentDisciplineRequestt) {
        if (studentDisciplineRequestt.getStudentId() != null && studentDisciplineRequestt.getStudentId().intValue() > 0) {
            Optional<Student> student = studentRepository.findById(studentDisciplineRequestt.getStudentId());
            if (student.isPresent()) {
               return student.get();
            }
        }

        return null;
    }
}
