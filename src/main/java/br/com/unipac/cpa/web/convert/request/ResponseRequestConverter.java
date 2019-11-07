package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.*;
import br.com.unipac.cpa.model.repository.*;
import br.com.unipac.cpa.web.dto.request.ResponseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Optional;

@Component
public class ResponseRequestConverter implements Converter<ResponseRequest, Response> {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private ProfessorDisciplineRepository professorDisciplineRepository;

    @Autowired
    private StudentDisciplineRepository studentDisciplineRepository;

    @Override
    public Response convert(ResponseRequest responseRequest) {
        Optional<Question> question = Optional.empty();
        Optional<Choice> choice = Optional.empty();
        Optional<Student> student = Optional.empty();
        Optional<ProfessorDiscipline> professorDiscipline = Optional.empty();
        Optional<StudentDiscipline> studentDiscipline = Optional.empty();

        Response response = Response.builder()
                .title(responseRequest.getTitle())
                .description(responseRequest.getDescription()).build();


        if (responseRequest.getQuestionId() != null && responseRequest.getQuestionId().intValue() > 0) {
            question = questionRepository.findById(responseRequest.getQuestionId());
            if (question.isPresent()) {
                response.setQuestion(question.get());
            }
        }

        if (responseRequest.getChoiceId() != null && responseRequest.getChoiceId().intValue() > 0) {
            choice = choiceRepository.findById(responseRequest.getChoiceId());
            if (choice.isPresent()) {
                response.setChoice(choice.get());
            }
        }

        if (responseRequest.getProfessorDisciplineId() != null && responseRequest.getProfessorDisciplineId().intValue() > 0) {
            professorDiscipline = professorDisciplineRepository.findById(responseRequest.getProfessorDisciplineId());
            if (professorDiscipline.isPresent()) {
                response.setProfessorDiscipline(professorDiscipline.get());
            }
        }

        if (responseRequest.getStudentDisciplineId() != null && responseRequest.getStudentDisciplineId().intValue() > 0) {
            studentDiscipline = studentDisciplineRepository.findById(responseRequest.getStudentDisciplineId());
            if (studentDiscipline.isPresent()) {
                response.setStudentDiscipline(studentDiscipline.get());
            }
        }

        return response;
    }
}
