package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.repository.PeriodRepository;
import br.com.unipac.cpa.web.dto.request.DisciplineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DisciplineRequestConverter implements Converter<DisciplineRequest, Discipline> {

    @Autowired
    private PeriodRepository periodRepository;

    @Override
    public Discipline convert(DisciplineRequest disciplineRequest) {
        Optional<Period> period = Optional.empty();
        if (disciplineRequest.getPeriodId() != 0) {
            period = periodRepository.findById(disciplineRequest.getPeriodId());
        }

        return Discipline.builder()
                .name(disciplineRequest.getName())
                .description(disciplineRequest.getDescription())
                .period(period.isPresent() ? period.get() : new Period())
                .build();
    }
}
