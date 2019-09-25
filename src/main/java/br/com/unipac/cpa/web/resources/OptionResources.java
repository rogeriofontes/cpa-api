package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.model.domain.Option;
import br.com.unipac.cpa.model.service.OptionService;
import br.com.unipac.cpa.web.dto.request.CourseRequest;
import br.com.unipac.cpa.web.dto.response.CourseResponse;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/options")
public class OptionResources {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OptionService optionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<Option>> get() {
        List<Option> options = optionService.listAll();

        if (!options.isEmpty()) {
            ResponseEntity.ok(options);
        }

        return null;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Option> add(@Valid @RequestBody Option option) {

        Option option1 = optionService.save(option);
        if (option1 != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(option.getDescription())
                    .toUri();
            ResponseEntity.created(location).build();
        }

        return null;
    }
}
