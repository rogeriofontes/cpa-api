package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.repository.CourseRepository;
import br.com.unipac.cpa.web.dto.request.PeriodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PeriodRequestConverter implements Converter<PeriodRequest, Period> {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Period convert(PeriodRequest periodRequest) {
        Optional<Course> course = Optional.empty();

        if (periodRequest.getCourseId() != null && periodRequest.getCourseId().intValue() > 0) {
            course = courseRepository.findById(periodRequest.getCourseId());
        }

        return Period.builder()
                .name(periodRequest.getName())
                .representationNumber(periodRequest.getRepresentationNumber())
                .course(course.isPresent() ? course.get() : new Course()).build();
    }
}
