package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.service.PeriodService;
import br.com.unipac.cpa.web.dto.request.PeriodRequest;
import br.com.unipac.cpa.web.dto.response.PeriodResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PeriodSupport {

    private static final Logger log = LogManager.getLogger(PeriodSupport.class);

    @Autowired
    private PeriodService service;

    @Autowired
    private ConversionService conversion;

    public PeriodResponse convertToFindById(Long id) {
        PeriodResponse founded = null;
        Optional<Period> period = service.findById(id);

        if (period.isPresent()) {
            founded = conversion.convert(period.get(), PeriodResponse.class);
            if (founded != null)
                log.info("Company: {} ", founded);
        } else {
            throw new ResourceNotFoundException("Period not found");
        }

        return founded;
    }

    public PeriodResponse convertToFindByName(String name) {
        PeriodResponse founded = null;
        Optional<Period> period = service.findByName(name);

        if (period.isPresent()) {
            founded = conversion.convert(period.get(), PeriodResponse.class);
            if (founded != null)
                log.info("Company: {} ", founded);
        } else {
            throw new ResourceNotFoundException("Period not found");
        }
        return founded;
    }

    public List<PeriodResponse> list() {
        List<PeriodResponse> periodsResponse = new ArrayList<>();

        List<Period> periods = service.listAll();
        log.info("size: " + periods.size());

        if (!periods.isEmpty()) {
            periods.forEach(period -> {
                PeriodResponse saved = conversion.convert(period, PeriodResponse.class);
                periodsResponse.add(saved);
            });
        }

        return periodsResponse;
    }

    public PeriodResponse convertToCreate(PeriodRequest periodRequest) {
        Period period = conversion.convert(periodRequest, Period.class);
        Period result = service.save(period);
        return conversion.convert(result, PeriodResponse.class);
    }

    public PeriodResponse convertToChange(Long id, PeriodRequest periodRequest) {
        Period period = conversion.convert(periodRequest, Period.class);
        Period result = service.edit(id, period);
        return conversion.convert(result, PeriodResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
