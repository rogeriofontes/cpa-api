package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.domain.LikertSkala;
import br.com.unipac.cpa.model.repository.CourseRepository;
import br.com.unipac.cpa.web.dto.request.LikertSkalaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LikertSkalaRequestConverter implements Converter<LikertSkalaRequest, LikertSkala> {

    @Override
    public LikertSkala convert(LikertSkalaRequest likertSkalaRequest) {

        return LikertSkala.builder()
                .name(likertSkalaRequest.getName()).build();
    }
}
