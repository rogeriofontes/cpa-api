package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
import br.com.unipac.cpa.web.dto.request.DisciplineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DisciplineRequestConverter implements Converter<DisciplineRequest, Discipline> {

    @Autowired
    private ProfessorRepository pofessorRepository;

    @Override
    public Discipline convert(DisciplineRequest disciplineRequest) {
        Optional<Professor> professor = Optional.empty();
        if (disciplineRequest.getProfessorId() != 0) {
            professor = pofessorRepository.findById(disciplineRequest.getProfessorId());
        }

       return Discipline.builder()
                .name(disciplineRequest.getName())
                .description(disciplineRequest.getDescription())
                .professor(professor.get()).build();
    }
}
