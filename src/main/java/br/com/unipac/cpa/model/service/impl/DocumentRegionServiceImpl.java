package br.com.unipac.cpa.model.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.service.DocumentRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DocumentRegionServiceImpl implements DocumentRegionService {

	@Override
	@Cacheable(Constants.DOCUMENT_REGIONS_IN_CACHE)
	public Optional<List<DocumentRegion>> all() {
		List<DocumentRegion> regions= DocumentRegion.getDocumentRegions();
		return Optional.of(regions);
	}

}
