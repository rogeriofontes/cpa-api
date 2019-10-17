package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.StudentDiscipline;
import br.com.unipac.cpa.web.dto.response.StudentDisciplineResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentDisciplineResponseConverter implements Converter<StudentDiscipline, StudentDisciplineResponse> {
    @Override
    public StudentDisciplineResponse convert(StudentDiscipline studentDiscipline) {
        return StudentDisciplineResponse.builder()
                .id(studentDiscipline.getId())
                .disciplineId(studentDiscipline.getDiscipline().getId())
                .studentId(studentDiscipline.getStudent().getId()).build();
    }
}
