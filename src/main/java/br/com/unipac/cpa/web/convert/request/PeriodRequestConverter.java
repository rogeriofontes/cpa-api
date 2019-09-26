package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
import br.com.unipac.cpa.web.dto.request.PeriodRequest;
import br.com.unipac.cpa.web.dto.response.PeriodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PeriodRequestConverter implements Converter<PeriodRequest, Period> {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public Period convert(PeriodRequest periodRequest) {
        Optional<Professor> professor = Optional.empty();

        if (periodRequest.getProfessorId() != null && periodRequest.getProfessorId().intValue() > 0) {
            professor = professorRepository.findById(periodRequest.getProfessorId());
        }

        return Period.builder()
                .name(periodRequest.getName())
                .description(periodRequest.getDescription())
                .professor(professor.get()).build();
    }
}
