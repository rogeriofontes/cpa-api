package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Response;
import br.com.unipac.cpa.model.repository.ResponseRepository;
import br.com.unipac.cpa.model.service.ResponseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseServiceImpl implements ResponseService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ResponseRepository pesponseRepository;
	private Iterable<Response> itr;

	@Override
	public Response save(Response e) {
		return pesponseRepository.save(e);
	}

	@Override
	public Response edit(Long id, Response pesponse) {
		if (id != null && pesponse != null) {
			Optional<Response> result = findById(id);

			if (result.isPresent()) {
				pesponse.setId(id);
				return pesponseRepository.save(pesponse);
			} else {
				throw new ResourceNotFoundException("BusinessFrontTypee não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<Response> findById(Long id) {
		return pesponseRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.PERIODS_IN_CACHE)
	public List<Response> listAll() {
		Iterable<Response> itr = pesponseRepository.findAll();
		return (List<Response>) itr;
	}

	@Override
	@Cacheable(Constants.PERIODS_IN_CACHE)
	public Page<Response> findAllPageable(Pageable pageable) {
		return pesponseRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Response> result = findById(id);

		if (result != null) {
			pesponseRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
