package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.service.DocumentRegionService;
import br.com.unipac.cpa.web.dto.response.DocumentRegionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DocumentRegionSupport {

	@Autowired
	private DocumentRegionService service;

	@Autowired
	private ConversionService documentRegionConvert;

	public List<DocumentRegionResponse> list() {
		List<DocumentRegionResponse> documentRegions = new ArrayList<>();
		Optional<List<DocumentRegion>> list = service.all();
		
		if (list.isPresent()) {
			list.get().forEach(documentRegion -> {
				DocumentRegionResponse saved = documentRegionConvert.convert(documentRegion, DocumentRegionResponse.class);
				documentRegions.add(saved);
			});
			
			return documentRegions;
		}
		
		return Collections.emptyList();
	}
}
