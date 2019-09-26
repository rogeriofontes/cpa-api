package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.ChoiceRequest;
import br.com.unipac.cpa.web.dto.response.ChoiceResponse;
import br.com.unipac.cpa.web.support.ChoiceSupport;
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
@RequestMapping("/v1/choices")
public class ChoiceResources {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChoiceSupport conversionSupport;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<?> getAll() {
        logger.info("teste");
        List<ChoiceResponse> result = conversionSupport.list();

        if (result != null) {
            logger.info(Constants.TOTAL + result.size());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<ChoiceResponse> get(@PathVariable("id") Long id) {
        ChoiceResponse result = conversionSupport.convertToFindById(id);

        if (result != null) {
            logger.info(Constants.TOTAL + result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Timed
    @CacheEvict(value = Constants.CHOICES_IN_CACHE, allEntries = true)
    public ResponseEntity<ChoiceResponse> add(@Valid @RequestBody ChoiceRequest choiceRequest) {
        ChoiceResponse result = conversionSupport.convertToCreate(choiceRequest);

        if (result != null) {
            logger.info(Constants.TOTAL + result.toString());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
                    .toUri();
            return ResponseEntity.created(location).body(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Timed
    @CacheEvict(value = Constants.CHOICES_IN_CACHE, allEntries = true)
    public ResponseEntity<ChoiceResponse> change(@PathVariable Long id, @RequestBody ChoiceRequest choiceRequest) {
        ChoiceResponse result = conversionSupport.convertToChange(id, choiceRequest);

        if (result != null) {
            logger.info(Constants.TOTAL + result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Timed
    @CacheEvict(value = Constants.CHOICES_IN_CACHE, allEntries = true)
    public ResponseEntity<?> remove(@PathVariable Long id) {
        boolean result = conversionSupport.remove(id);
        if (result) {
            return ResponseEntity.ok(Constants.DADOS_DELETADOS);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
