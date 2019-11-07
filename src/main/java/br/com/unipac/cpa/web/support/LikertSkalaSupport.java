package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.LikertSkala;
import br.com.unipac.cpa.model.service.LikertSkalaService;
import br.com.unipac.cpa.web.dto.request.LikertSkalaRequest;
import br.com.unipac.cpa.web.dto.response.LikertSkalaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LikertSkalaSupport {

    private static final Logger log = LogManager.getLogger(LikertSkalaSupport.class);

    @Autowired
    private LikertSkalaService service;

    @Autowired
    private ConversionService conversion;

    public LikertSkalaResponse convertToFindById(Long id) {
        LikertSkalaResponse founded = null;
        Optional<LikertSkala> likertSkala = service.findById(id);

        if (likertSkala.isPresent()) {
            founded = conversion.convert(likertSkala.get(), LikertSkalaResponse.class);
            log.info("LikertSkala: " + founded.toString());
        } else {
            throw new ResourceNotFoundException("Company Type not found");
        }

        return founded;
    }

    public List<LikertSkalaResponse> list() {
        List<LikertSkalaResponse> likertSkalas = new ArrayList<>();
        service.listAll().forEach(likertSkala -> {
            LikertSkalaResponse saved = conversion.convert(likertSkala, LikertSkalaResponse.class);
            likertSkalas.add(saved);
        });
        return likertSkalas;
    }

    public LikertSkalaResponse convertToCreate(LikertSkalaRequest likertSkalaRequest) {
        LikertSkala likertSkala = conversion.convert(likertSkalaRequest, LikertSkala.class);
        LikertSkala result = service.save(likertSkala);
        return conversion.convert(result, LikertSkalaResponse.class);
    }

    public LikertSkalaResponse convertToChange(Long id, LikertSkalaRequest likertSkalaRequest) {
        LikertSkala likertSkala = conversion.convert(likertSkalaRequest, LikertSkala.class);
        LikertSkala result = service.edit(id, likertSkala);
        return conversion.convert(result, LikertSkalaResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
