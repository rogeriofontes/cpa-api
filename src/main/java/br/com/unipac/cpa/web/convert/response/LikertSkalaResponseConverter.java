package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.LikertSkala;
import br.com.unipac.cpa.web.dto.response.LikertSkalaResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LikertSkalaResponseConverter implements Converter<LikertSkala, LikertSkalaResponse> {
    @Override
    public LikertSkalaResponse convert(LikertSkala likertSkala) {
        return LikertSkalaResponse.builder()
                .id(likertSkala.getId())
                .name(likertSkala.getName()).build();
    }
}
