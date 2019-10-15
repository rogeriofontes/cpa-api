package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.web.dto.response.DisciplineResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DisciplineResponseConverter implements Converter<Discipline, DisciplineResponse> {
    @Override
    public DisciplineResponse convert(Discipline discipline) {
        return DisciplineResponse.builder()
                .id(discipline.getId())
                .name(discipline.getName())
                .description(discipline.getDescription())
                .periodId(discipline.getPeriod().getId()).build();
    }
}
