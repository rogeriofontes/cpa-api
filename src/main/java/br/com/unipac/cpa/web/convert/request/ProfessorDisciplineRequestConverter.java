package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.domain.ProfessorDiscipline;
import br.com.unipac.cpa.model.repository.CourseRepository;
import br.com.unipac.cpa.model.repository.DisciplineRepository;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
import br.com.unipac.cpa.web.dto.request.ProfessorDisciplineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfessorDisciplineRequestConverter implements Converter<ProfessorDisciplineRequest, ProfessorDiscipline> {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public ProfessorDiscipline convert(ProfessorDisciplineRequest professorDisciplineRequest) {
        Optional<Discipline> discipline = Optional.empty();
        Optional<Professor> professor = Optional.empty();

        ProfessorDiscipline professorDiscipline = new ProfessorDiscipline();

        if (professorDisciplineRequest.getProfessorId() != null && professorDisciplineRequest.getProfessorId().intValue() > 0) {
            professor = professorRepository.findById(professorDisciplineRequest.getProfessorId());
            if (professor.isPresent()) {
                professorDiscipline.setProfessor(professor.get());
            }
        }

        if (professorDisciplineRequest.getDisciplineId() != null && professorDisciplineRequest.getDisciplineId().intValue() > 0) {
            discipline = disciplineRepository.findById(professorDisciplineRequest.getDisciplineId());
            if (discipline.isPresent()) {
                professorDiscipline.setDiscipline(discipline.get());
            }
        }

        return professorDiscipline;
    }
}
