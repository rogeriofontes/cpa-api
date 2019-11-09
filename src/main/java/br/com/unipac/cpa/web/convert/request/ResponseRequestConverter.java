package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.*;
import br.com.unipac.cpa.model.repository.ChoiceRepository;
import br.com.unipac.cpa.model.repository.ProfessorDisciplineRepository;
import br.com.unipac.cpa.model.repository.QuestionRepository;
import br.com.unipac.cpa.model.repository.StudentDisciplineRepository;
import br.com.unipac.cpa.web.dto.request.ResponseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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
        return Response.builder()
                .title(responseRequest.getTitle())
                .studentDiscipline(getStudentDiscipline(responseRequest))
                .professorDiscipline(getProfessorDiscipline(responseRequest))
                .choice(getChoice(responseRequest))
                .question(getQuestion(responseRequest))
                .description(responseRequest.getDescription()).build();
    }

    private StudentDiscipline getStudentDiscipline(ResponseRequest responseRequest) {
        if (responseRequest.getStudentDisciplineId() != null && responseRequest.getStudentDisciplineId().intValue() > 0) {
            Optional<StudentDiscipline> studentDiscipline = studentDisciplineRepository.findById(responseRequest.getStudentDisciplineId());
            if (studentDiscipline.isPresent()) {
               return studentDiscipline.get();
            }
        }

        return null;
    }

    private ProfessorDiscipline getProfessorDiscipline(ResponseRequest responseRequest) {
        if (responseRequest.getProfessorDisciplineId() != null && responseRequest.getProfessorDisciplineId().intValue() > 0) {
            Optional<ProfessorDiscipline> professorDiscipline = professorDisciplineRepository.findById(responseRequest.getProfessorDisciplineId());
            if (professorDiscipline.isPresent()) {
               return  professorDiscipline.get();
            }
        }

        return null;
    }

    private Choice getChoice(ResponseRequest responseRequest) {
        if (responseRequest.getChoiceId() != null && responseRequest.getChoiceId().intValue() > 0) {
            Optional<Choice> choice = choiceRepository.findById(responseRequest.getChoiceId());
            if (choice.isPresent()) {
                return choice.get();
            }
        }

        return null;
    }

    private Question getQuestion(ResponseRequest responseRequest) {
        if (responseRequest.getQuestionId() != null && responseRequest.getQuestionId().intValue() > 0) {
            Optional<Question> question = questionRepository.findById(responseRequest.getQuestionId());
            if (question.isPresent()) {
                return question.get();
            }
        }

        return null;
    }
}
