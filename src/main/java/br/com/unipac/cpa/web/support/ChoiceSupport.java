package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Choice;
import br.com.unipac.cpa.model.service.ChoiceService;
import br.com.unipac.cpa.web.dto.request.ChoiceRequest;
import br.com.unipac.cpa.web.dto.response.ChoiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ChoiceSupport {

    @Autowired
    private ChoiceService service;

    @Autowired
    private ConversionService conversion;

    public ChoiceResponse convertToFindById(Long id) {
        ChoiceResponse founded = null;
        Optional<Choice> choice = service.findById(id);

        if (choice.isPresent()) {
            founded = conversion.convert(choice.get(), ChoiceResponse.class);
            if (founded != null)
                log.info("Company: {} ", founded);
        } else {
            throw new ResourceNotFoundException("Company Type not found");
        }

        return founded;
    }

    public List<ChoiceResponse> list() {
        List<ChoiceResponse> choices = new ArrayList<>();
        service.listAll().forEach(choice -> {
            ChoiceResponse saved = conversion.convert(choice, ChoiceResponse.class);
            choices.add(saved);
        });
        return choices;
    }

    public ChoiceResponse convertToCreate(ChoiceRequest choiceRequest) {
        Choice choice = conversion.convert(choiceRequest, Choice.class);
        Choice result = service.save(choice);
        return conversion.convert(result, ChoiceResponse.class);
    }

    public ChoiceResponse convertToChange(Long id, ChoiceRequest choiceRequest) {
        Choice choice = conversion.convert(choiceRequest, Choice.class);
        Choice result = service.edit(id, choice);
        return conversion.convert(result, ChoiceResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
