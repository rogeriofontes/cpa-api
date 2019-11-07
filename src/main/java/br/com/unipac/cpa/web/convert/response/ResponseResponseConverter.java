package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Response;
import br.com.unipac.cpa.web.dto.response.ResponseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ResponseResponseConverter implements Converter<Response, ResponseResponse> {
    @Override
    public ResponseResponse convert(Response response) {
        return ResponseResponse.builder()
                .id(response.getId())
                .title(response.getTitle())
                .description(response.getDescription())
                .questionId(response.getQuestion().getId())
                .choiceId(response.getChoice().getId())
                .professorDisciplineId(response.getProfessorDiscipline().getId())
                .studentDisciplineId(response.getStudentDiscipline().getId()).build();
    }
}
