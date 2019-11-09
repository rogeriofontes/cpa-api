package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.CompanyRequest;
import br.com.unipac.cpa.web.dto.response.CompanyResponse;
import br.com.unipac.cpa.web.support.CompanySupport;
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
@RequestMapping(path = "/v1/companys")
public class CompanyResources {

	@Autowired
	private CompanySupport companySupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<CompanyResponse>> getAll() {
		List<CompanyResponse> result = companySupport.list();

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
	public ResponseEntity<CompanyResponse> get(@PathVariable("id") Long id) {
		CompanyResponse result = companySupport.convertToFindById(id);

		if (result != null) {
			log.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Timed
	@CacheEvict(value = Constants.COMPANYS_IN_CACHE, allEntries = true)
	public ResponseEntity<CompanyResponse> add(@Valid @RequestBody CompanyRequest companyRequest) {
		CompanyResponse result = companySupport.convertToCreate(companyRequest);

		if (result != null) {
			log.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Timed
	@CacheEvict(value = Constants.COMPANYS_IN_CACHE, allEntries = true)
	public ResponseEntity<CompanyResponse> change(@PathVariable Long id, @RequestBody CompanyRequest companyDTO) {
		CompanyResponse result = companySupport.convertToChange(id, companyDTO);

		if (result != null) {
			log.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Timed
	@CacheEvict(value = Constants.COMPANYS_IN_CACHE, allEntries = true)
	public ResponseEntity<String> remove(@PathVariable Long id) {
		boolean result = companySupport.remove(id);

		if (result) {
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/find")
	@ResponseBody
	@Timed
	public ResponseEntity<CompanyResponse> findByName(@RequestParam(value="name", required=false) String name) {
		CompanyResponse result = companySupport.convertToFindByName(name);

		if (result != null) {
			log.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
