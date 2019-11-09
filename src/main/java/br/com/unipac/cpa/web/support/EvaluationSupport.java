package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Evaluation;
import br.com.unipac.cpa.model.service.EvaluationService;
import br.com.unipac.cpa.web.dto.request.EvaluationRequest;
import br.com.unipac.cpa.web.dto.response.EvaluationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EvaluationSupport {

	private final Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private EvaluationService evaluationService;

	@Autowired
	private ConversionService conversionService;
	
	public EvaluationResponse convertToFindById(Long id) {
		EvaluationResponse founded = null;
		Optional<Evaluation> evaluation = evaluationService.findById(id);

		if (evaluation.isPresent()) {
			founded = conversionService.convert(evaluation.get(), EvaluationResponse.class);
			log.info("Evaluation %s", founded);
		} else {
			throw new ResourceNotFoundException("Evaluation not found");
		}
		return founded;
	}

	public EvaluationResponse convertToFindByName(String name) {
		EvaluationResponse founded = null;
		Optional<Evaluation> evaluation = evaluationService.findByName(name);

		if (evaluation.isPresent()) {
			founded = conversionService.convert(evaluation.get(), EvaluationResponse.class);
			if (founded != null)
				log.info("Company: {} ", founded);
		} else {
			throw new ResourceNotFoundException("Evaluation not found");
		}
		return founded;
	}

	public List<EvaluationResponse> list() {
		List<EvaluationResponse> evaluations = new ArrayList<>();
		evaluationService.listAll().forEach(evaluation -> {
			EvaluationResponse saved = conversionService.convert(evaluation, EvaluationResponse.class);
			evaluations.add(saved);
		});
		return evaluations;
	}

	public EvaluationResponse convertToCreate(EvaluationRequest evaluationRequest) {
		Evaluation evaluation = conversionService.convert(evaluationRequest, Evaluation.class);
		Evaluation saved = evaluationService.save(evaluation);
		return getConverter(saved);
	}

	private EvaluationResponse getConverter(Evaluation evaluation) {
		return conversionService.convert(evaluation, EvaluationResponse.class);
	}

	public EvaluationResponse convertToChange(Long id, EvaluationRequest evaluationRequest) {
		Evaluation evaluation = conversionService.convert(evaluationRequest, Evaluation.class);
		Evaluation updated = evaluationService.edit(id, evaluation);
		return getConverter(updated);
	}

	public boolean remove(Long id) {
		return evaluationService.remove(id);
	}
}
