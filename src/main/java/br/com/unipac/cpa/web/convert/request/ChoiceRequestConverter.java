package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Choice;
import br.com.unipac.cpa.web.dto.request.ChoiceRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ChoiceRequestConverter implements Converter<ChoiceRequest, Choice> {

    @Override
    public Choice convert(ChoiceRequest choiceRequest) {
        return Choice.builder()
                .description(choiceRequest.getDescription())
                .point(choiceRequest.getPoint()).build();
    }
}
