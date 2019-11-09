package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.ProfessorDisciplineRequest;
import br.com.unipac.cpa.web.dto.response.ProfessorDisciplineResponse;
import br.com.unipac.cpa.web.support.ProfessorDisciplineSupport;
import com.codahale.metrics.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/v1/professor-disciplines")
public class ProfessorDisciplineResources {

    @Autowired
    private ProfessorDisciplineSupport conversionSupport;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<List<ProfessorDisciplineResponse>> getAll() {
        log.info("teste");
        List<ProfessorDisciplineResponse> result = conversionSupport.list();

        if (result != null) {
            log.info(Constants.TOTAL + result.size());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<ProfessorDisciplineResponse> get(@PathVariable("id") Long id) {
        ProfessorDisciplineResponse result = conversionSupport.convertToFindById(id);

        if (result != null) {
            log.info(Constants.TOTAL + result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Timed
    @CacheEvict(value = Constants.PROFESSORS_DISCIPLINE_IN_CACHE, allEntries = true)
    public ResponseEntity<ProfessorDisciplineResponse> add(@Valid @RequestBody ProfessorDisciplineRequest professorDisciplineRequest) {
        ProfessorDisciplineResponse result = conversionSupport.convertToCreate(professorDisciplineRequest);

        if (result != null) {
            log.info(Constants.TOTAL + result.toString());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
                    .toUri();
            return ResponseEntity.created(location).body(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Timed
    @CacheEvict(value = Constants.PROFESSORS_DISCIPLINE_IN_CACHE, allEntries = true)
    public ResponseEntity<ProfessorDisciplineResponse> change(@PathVariable Long id, @RequestBody ProfessorDisciplineRequest professorDisciplineRequest) {
        ProfessorDisciplineResponse result = conversionSupport.convertToChange(id, professorDisciplineRequest);

        if (result != null) {
            log.info(Constants.TOTAL + result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Timed
    @CacheEvict(value = Constants.PROFESSORS_DISCIPLINE_IN_CACHE, allEntries = true)
    public ResponseEntity<String> remove(@PathVariable Long id) {
        boolean result = conversionSupport.remove(id);
        if (result) {
            return ResponseEntity.ok(Constants.DADOS_DELETADOS);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
