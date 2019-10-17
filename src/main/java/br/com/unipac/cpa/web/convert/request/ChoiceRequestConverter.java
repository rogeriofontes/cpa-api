package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Choice;
import br.com.unipac.cpa.model.domain.LikertSkala;
import br.com.unipac.cpa.model.service.LikertSkalaService;
import br.com.unipac.cpa.web.dto.request.ChoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChoiceRequestConverter implements Converter<ChoiceRequest, Choice> {

    @Autowired
    private LikertSkalaService likertSkalaService;

    @Override
    public Choice convert(ChoiceRequest choiceRequest) {
        Optional<LikertSkala> likertSkala = likertSkalaService.findById(choiceRequest.getLikertSkalaId());

        Choice choice = Choice.builder()
                .description(choiceRequest.getDescription())
                .point(choiceRequest.getPoint()).build();


        if(likertSkala.isPresent()) {
            choice.setLikertSkala(likertSkala.get());
        }

        return choice;
    }
}
