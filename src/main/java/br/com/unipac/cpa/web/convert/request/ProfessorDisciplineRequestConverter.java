package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.domain.ProfessorDiscipline;
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
        ProfessorDiscipline professorDiscipline = new ProfessorDiscipline();

        professorDiscipline.setProfessor(getProfessorDiscipline(professorDisciplineRequest));
        professorDiscipline.setDiscipline(getDiscipline(professorDisciplineRequest));

        return professorDiscipline;
    }

    private Professor getProfessorDiscipline(ProfessorDisciplineRequest professorDisciplineRequest) {
        if (professorDisciplineRequest.getProfessorId() != null && professorDisciplineRequest.getProfessorId().intValue() > 0) {
            Optional<Professor> professor = professorRepository.findById(professorDisciplineRequest.getProfessorId());
            if (professor.isPresent()) {
               return professor.get();
            }
        }

        return null;
    }

    private Discipline getDiscipline(ProfessorDisciplineRequest professorDisciplineRequest) {
        if (professorDisciplineRequest.getDisciplineId() != null && professorDisciplineRequest.getDisciplineId().intValue() > 0) {
            Optional<Discipline> discipline = disciplineRepository.findById(professorDisciplineRequest.getDisciplineId());
            if (discipline.isPresent()) {
                return discipline.get();
            }
        }

        return null;
    }
}
