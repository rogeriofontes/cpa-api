package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.ProfessorDiscipline;
import br.com.unipac.cpa.web.dto.response.ProfessorDisciplineResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfessorDisciplineResponseConverter implements Converter<ProfessorDiscipline, ProfessorDisciplineResponse> {
    @Override
    public ProfessorDisciplineResponse convert(ProfessorDiscipline professorDiscipline) {
        return ProfessorDisciplineResponse.builder()
                .id(professorDiscipline.getId())
                .disciplineId(professorDiscipline.getDiscipline().getId())
                .professorId(professorDiscipline.getProfessor().getId()).build();
    }
}
