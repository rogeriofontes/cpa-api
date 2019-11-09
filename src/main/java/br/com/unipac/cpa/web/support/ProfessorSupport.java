package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.service.ProfessorService;
import br.com.unipac.cpa.web.dto.request.ProfessorRequest;
import br.com.unipac.cpa.web.dto.response.ProfessorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProfessorSupport {

	private final Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private ConversionService conversionService;
	
	public ProfessorResponse convertToFindById(Long id) {
		ProfessorResponse founded = null;
		Optional<Professor> professor = professorService.findById(id);

		if (professor.isPresent()) {
			founded = conversionService.convert(professor.get(), ProfessorResponse.class);
			if (founded != null)
				log.info("Company: {} ", founded);
		} else {
			throw new ResourceNotFoundException("Professor not found");
		}
		return founded;
	}

	public ProfessorResponse convertToFindByName(String name) {
		ProfessorResponse founded = null;
		Optional<Professor> professor = professorService.findByName(name);

		if (professor.isPresent()) {
			founded = conversionService.convert(professor.get(), ProfessorResponse.class);
			if (founded != null)
				log.info("Company: {} ", founded);
		} else {
			throw new ResourceNotFoundException("Professor not found");
		}
		return founded;
	}

	public List<ProfessorResponse> list() {
		List<ProfessorResponse> professores = new ArrayList<>();
		professorService.listAll().forEach(professor -> {
			ProfessorResponse saved = conversionService.convert(professor, ProfessorResponse.class);
			professores.add(saved);
		});
		return professores;
	}

	public ProfessorResponse convertToCreate(ProfessorRequest professorRequest) {
		Professor professor = conversionService.convert(professorRequest, Professor.class);
		Professor saved = professorService.save(professor);
		return getConverter(saved);
	}

	private ProfessorResponse getConverter(Professor professor) {
		return conversionService.convert(professor, ProfessorResponse.class);
	}

	public ProfessorResponse convertToChange(Long id, ProfessorRequest professorRequest) {
		Professor professor = conversionService.convert(professorRequest, Professor.class);
		Professor updated = professorService.edit(id, professor);
		return getConverter(updated);
	}

	public boolean remove(Long id) {
		return professorService.remove(id);
	}
}
