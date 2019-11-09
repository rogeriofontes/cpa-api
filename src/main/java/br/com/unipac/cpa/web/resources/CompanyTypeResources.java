package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.CompanyTypeRequest;
import br.com.unipac.cpa.web.dto.response.CompanyTypeResponse;
import br.com.unipac.cpa.web.support.CompanyTypeSupport;
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
@RequestMapping(path = "/v1/company-types")
public class CompanyTypeResources {

	@Autowired
	private CompanyTypeSupport conversionSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<CompanyTypeResponse>> getAll() {
		log.info("teste");
		List<CompanyTypeResponse> result = conversionSupport.list();

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
	public ResponseEntity<CompanyTypeResponse> get(@PathVariable("id") Long id) {
		CompanyTypeResponse result = conversionSupport.convertToFindById(id);
		
		if (result != null) {
			log.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Timed
	@CacheEvict(value = Constants.COMPANYS_TYPES_IN_CACHE, allEntries = true)
	public ResponseEntity<CompanyTypeResponse> add(@Valid @RequestBody CompanyTypeRequest companyTypeRequest) {
		CompanyTypeResponse result = conversionSupport.convertToCreate(companyTypeRequest);

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
	@CacheEvict(value = Constants.COMPANYS_TYPES_IN_CACHE, allEntries = true)
	public ResponseEntity<CompanyTypeResponse> change(@PathVariable Long id, @RequestBody CompanyTypeRequest companyTypeRequest) {
		CompanyTypeResponse result = conversionSupport.convertToChange(id, companyTypeRequest);

		if (result != null) {
			log.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Timed
	@CacheEvict(value = Constants.COMPANYS_TYPES_IN_CACHE, allEntries = true)
	public ResponseEntity<String> remove(@PathVariable Long id) {
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
	public ResponseEntity<CompanyTypeResponse> findByName(@RequestParam(value="name", required=false) String name) {
		CompanyTypeResponse result = conversionSupport.convertToFindByName(name);

		if (result != null) {
			log.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
