package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.CourseRequest;
import br.com.unipac.cpa.web.dto.response.CourseResponse;
import br.com.unipac.cpa.web.support.CourseSupport;
import com.codahale.metrics.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/courses")
public class CourseResources {

	@Autowired
	private CourseSupport conversionSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<CourseResponse>> list() {
		List<CourseResponse> courseRequests = conversionSupport.list();

		if (courseRequests != null) {
			log.info(Constants.TOTAL + courseRequests.size());
			return ResponseEntity.ok(courseRequests);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<CourseResponse> get(@PathVariable("id") Long id) {
		CourseResponse result = conversionSupport.convertToFindById(id);

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
	@CacheEvict(value = Constants.COURSES_IN_CACHE, allEntries = true)
	public ResponseEntity<CourseResponse> add(@Valid @RequestBody CourseRequest courseRequest) {
		CourseResponse result = conversionSupport.convertToCreate(courseRequest);

		if (result != null) {
			log.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = Constants.COURSES_IN_CACHE, allEntries = true)
	public ResponseEntity<CourseResponse> change(@PathVariable("id") Long id,
												 @RequestBody CourseRequest courseRequest) {
		CourseResponse result = conversionSupport.convertToChange(id, courseRequest);
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
	@CacheEvict(value = Constants.COURSES_IN_CACHE, allEntries = true)
	public ResponseEntity<String> remove(@PathVariable("id") Long id) {
		boolean result = conversionSupport.remove(id);
		if (result) {
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/find")
	@ResponseBody
	@Timed
	public ResponseEntity<CourseResponse> findByName(@RequestParam(value="name", required=false) String name) {
		CourseResponse result = conversionSupport.convertToFindByName(name);

		if (result != null) {
			log.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
