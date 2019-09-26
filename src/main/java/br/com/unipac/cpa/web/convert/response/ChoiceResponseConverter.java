package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Choice;
import br.com.unipac.cpa.web.dto.response.ChoiceResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ChoiceResponseConverter implements Converter<Choice, ChoiceResponse> {
    @Override
    public ChoiceResponse convert(Choice choice) {
        return ChoiceResponse.builder()
                .id(choice.getId())
                .description(choice.getDescription())
                .point(choice.getPoint()).build();
    }
}
