package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.repository.CourseRepository;
import br.com.unipac.cpa.model.repository.PeriodRepository;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
import br.com.unipac.cpa.web.dto.request.DisciplineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class DisciplineRequestConverter implements Converter<DisciplineRequest, Discipline> {

    @Autowired
    private PeriodRepository periodRepository;

    @Override
    public Discipline convert(DisciplineRequest disciplineRequest) {
        Optional<Period> period = Optional.empty();
        if (disciplineRequest.getPeriodId() != 0) {
            period = periodRepository.findById(disciplineRequest.getPeriodId());
        }

       Discipline discipline = Discipline.builder()
                .name(disciplineRequest.getName())
                .description(disciplineRequest.getDescription())
                .period(period.get())
                .build();

        /*if (disciplineRequest.getProfessors() != null && disciplineRequest.getProfessors().size() > 0) {
            discipline.setProfessors(disciplineRequest.getProfessors());
        }

        if (disciplineRequest.getProfessors() != null && disciplineRequest.getProfessors().size() > 0) {
            discipline.setStudents(disciplineRequest.getStudents());
        }*/

        return  discipline;
    }
}
