package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.model.service.QuestionService;
import br.com.unipac.cpa.web.dto.request.QuestionRequest;
import br.com.unipac.cpa.web.dto.response.QuestionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class QuestionSupport {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ConversionService conversionQuestion;

    public QuestionResponse convertToFindById(Long id) {
        QuestionResponse founded = null;
        Optional<Question> question = questionService.findById(id);

        if (question.isPresent()) {
            founded = conversionQuestion.convert(question.get(), QuestionResponse.class);
            if (founded != null)
                log.info("Company: {} ", founded);
        } else {
            throw new ResourceNotFoundException("Question not found");
        }
        return founded;
    }

    public QuestionResponse convertToFindByTitle(String title) {
        QuestionResponse founded = null;
        Optional<Question> question = questionService.findByTitle(title);

        if (question.isPresent()) {
            founded = conversionQuestion.convert(question.get(), QuestionResponse.class);
            if (founded != null)
                log.info("Company: {} ", founded);
        } else {
            throw new ResourceNotFoundException("Question not found");
        }
        return founded;
    }

    public List<QuestionResponse> list() {
        List<QuestionResponse> questions = new ArrayList<>();
        questionService.listAll().forEach(question -> {
            QuestionResponse saved = conversionQuestion.convert(question, QuestionResponse.class);
            questions.add(saved);
        });
        return questions;
    }

    public QuestionResponse convertToCreate(QuestionRequest questionRequest) {
        Question question = conversionQuestion.convert(questionRequest, Question.class);
        Question saved = questionService.save(question);
        return getConverter(saved);
    }

    private QuestionResponse getConverter(Question question) {
        return conversionQuestion.convert(question, QuestionResponse.class);
    }

    public QuestionResponse convertToChange(Long id, QuestionRequest questionRequest) {
        Question question = conversionQuestion.convert(questionRequest, Question.class);
        Question updated = questionService.edit(id, question);
        return getConverter(updated);
    }

    public boolean remove(Long id) {
        return questionService.remove(id);
    }
}
