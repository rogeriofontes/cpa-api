package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.LikertSkala;
import br.com.unipac.cpa.web.dto.request.LikertSkalaRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LikertSkalaRequestConverter implements Converter<LikertSkalaRequest, LikertSkala> {

    @Override
    public LikertSkala convert(LikertSkalaRequest likertSkalaRequest) {

        return LikertSkala.builder()
                .name(likertSkalaRequest.getName()).build();
    }
}
