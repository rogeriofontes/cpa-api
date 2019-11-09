package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.ProfessorDiscipline;
import br.com.unipac.cpa.model.service.ProfessorDisciplineService;
import br.com.unipac.cpa.web.dto.request.ProfessorDisciplineRequest;
import br.com.unipac.cpa.web.dto.response.ProfessorDisciplineResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProfessorDisciplineSupport {

    private static final Logger log = LogManager.getLogger(ProfessorDisciplineSupport.class);

    @Autowired
    private ProfessorDisciplineService service;

    @Autowired
    private ConversionService conversion;

    public ProfessorDisciplineResponse convertToFindById(Long id) {
        ProfessorDisciplineResponse founded = null;
        Optional<ProfessorDiscipline> professorDiscipline = service.findById(id);

        if (professorDiscipline.isPresent()) {
            founded = conversion.convert(professorDiscipline.get(), ProfessorDisciplineResponse.class);
            if (founded != null)
                log.info("Company: {} ", founded);
        } else {
            throw new ResourceNotFoundException("Company Type not found");
        }

        return founded;
    }

    public List<ProfessorDisciplineResponse> list() {
        List<ProfessorDisciplineResponse> professorDisciplines = new ArrayList<>();
        service.listAll().forEach(professorDiscipline -> {
            ProfessorDisciplineResponse saved = conversion.convert(professorDiscipline, ProfessorDisciplineResponse.class);
            professorDisciplines.add(saved);
        });
        return professorDisciplines;
    }

    public ProfessorDisciplineResponse convertToCreate(ProfessorDisciplineRequest professorDisciplineRequest) {
        ProfessorDiscipline professorDiscipline = conversion.convert(professorDisciplineRequest, ProfessorDiscipline.class);
        ProfessorDiscipline result = service.save(professorDiscipline);
        return conversion.convert(result, ProfessorDisciplineResponse.class);
    }

    public ProfessorDisciplineResponse convertToChange(Long id, ProfessorDisciplineRequest professorDisciplineRequest) {
        ProfessorDiscipline professorDiscipline = conversion.convert(professorDisciplineRequest, ProfessorDiscipline.class);
        ProfessorDiscipline result = service.edit(id, professorDiscipline);
        return conversion.convert(result, ProfessorDisciplineResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
