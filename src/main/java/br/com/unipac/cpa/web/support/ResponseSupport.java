package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Response;
import br.com.unipac.cpa.model.service.ResponseService;
import br.com.unipac.cpa.web.dto.request.ResponseRequest;
import br.com.unipac.cpa.web.dto.response.ResponseResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ResponseSupport {

    private static final Logger log = LogManager.getLogger(ResponseSupport.class);

    @Autowired
    private ResponseService service;

    @Autowired
    private ConversionService conversion;

    public ResponseResponse convertToFindById(Long id) {
        ResponseResponse founded = null;
        Optional<Response> response = service.findById(id);

        if (response.isPresent()) {
            founded = conversion.convert(response.get(), ResponseResponse.class);
            log.info("Response: " + founded.toString());
        } else {
            throw new ResourceNotFoundException("Company Type not found");
        }

        return founded;
    }

    public List<ResponseResponse> list() {
        List<ResponseResponse> responses = new ArrayList<>();
        service.listAll().forEach(response -> {
            ResponseResponse saved = conversion.convert(response, ResponseResponse.class);
            responses.add(saved);
        });
        return responses;
    }

    public ResponseResponse convertToCreate(ResponseRequest responseRequest) {
        Response response = conversion.convert(responseRequest, Response.class);
        Response result = service.save(response);
        return conversion.convert(result, ResponseResponse.class);
    }

    public ResponseResponse convertToChange(Long id, ResponseRequest responseRequest) {
        Response response = conversion.convert(responseRequest, Response.class);
        Response result = service.edit(id, response);
        return conversion.convert(result, ResponseResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
