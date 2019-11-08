package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.QuestionRequest;
import br.com.unipac.cpa.web.dto.response.QuestionResponse;
import br.com.unipac.cpa.web.support.QuestionSupport;
import com.codahale.metrics.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/questions")
public class QuestionResources {

    @Autowired
    private QuestionSupport questionSupport;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<List<QuestionResponse>> list() {
        List<QuestionResponse> questions = questionSupport.list();

        if (questions != null) {
            log.info(Constants.TOTAL + questions.size());
            return ResponseEntity.ok(questions);
        } else {
            return  ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<QuestionResponse> get(@PathVariable("id") Long id) {
        QuestionResponse result = questionSupport.convertToFindById(id);

        if (result != null) {
            log.info(Constants.TOTAL + result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    @CacheEvict(value = Constants.QUESTION_IN_CACHE, allEntries = true)
    public ResponseEntity<QuestionResponse> add(@RequestBody QuestionRequest questionRequest) {
        QuestionResponse result = questionSupport.convertToCreate(questionRequest);

        if (result != null) {
            log.info(Constants.TOTAL + result.toString());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
                    .toUri();
            return ResponseEntity.created(location).body(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    @CacheEvict(value = Constants.QUESTION_IN_CACHE, allEntries = true)
    public ResponseEntity<QuestionResponse> change(@PathVariable("id") Long id, @RequestBody QuestionRequest questionRequest) {
        QuestionResponse result = questionSupport.convertToChange(id, questionRequest);

        if (result != null) {
            log.info(Constants.TOTAL + result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    @CacheEvict(value = Constants.QUESTION_IN_CACHE, allEntries = true)
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean result = questionSupport.remove(id);

        if (result) {
            return ResponseEntity.ok(Constants.DADOS_DELETADOS);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "/find-by-title")
    @ResponseBody
    @Timed
    public ResponseEntity<QuestionResponse> findByTitle(@RequestParam(value="title", required=false) String title) {
        QuestionResponse result = questionSupport.convertToFindByTitle(title);

        if (result != null) {
            log.info(Constants.TOTAL + result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
