package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.service.CourseService;
import br.com.unipac.cpa.web.dto.request.CourseRequest;
import br.com.unipac.cpa.web.dto.response.CourseResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CourseSupport {

    private static final Logger log = LogManager.getLogger(CourseSupport.class);

    @Autowired
    private CourseService service;

    @Autowired
    private ConversionService conversion;

    public CourseResponse convertToFindById(Long id) {
        CourseResponse founded = null;
        Optional<Course> course = service.findById(id);

        if (course.isPresent()) {
            founded = conversion.convert(course.get(), CourseResponse.class);
            if (founded != null)
                log.info("Company: {} ", founded);
        } else {
            throw new ResourceNotFoundException("Course not found");
        }
        return founded;
    }

    public CourseResponse convertToFindByName(String name) {
        CourseResponse founded = null;
        Optional<Course> course = service.findByName(name);

        if (course.isPresent()) {
            founded = conversion.convert(course.get(), CourseResponse.class);
            if (founded != null)
                log.info("Company: {} ", founded);
        } else {
            throw new ResourceNotFoundException("Course not found");
        }
        return founded;
    }

    public List<CourseResponse> list() {
        List<CourseResponse> courses = new ArrayList<>();
        service.listAll().forEach(course -> {
            CourseResponse saved = conversion.convert(course, CourseResponse.class);
            courses.add(saved);
        });
        return courses;
    }

    public CourseResponse convertToCreate(CourseRequest courseRequest) {
        Course course = conversion.convert(courseRequest, Course.class);
        Course result = service.save(course);
        return conversion.convert(result, CourseResponse.class);
    }

    public CourseResponse convertToChange(Long id, CourseRequest courseRequest) {
        Course course = conversion.convert(courseRequest, Course.class);
        Course result = service.edit(id, course);
        return conversion.convert(result, CourseResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
