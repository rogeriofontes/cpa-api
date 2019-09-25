package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.model.service.QuestionService;
import br.com.unipac.cpa.web.dto.request.QuestionRequest;
import br.com.unipac.cpa.web.dto.response.QuestionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class QuestionSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ConversionService conversionQuestion;

    public QuestionResponse convertToFindById(Long id) {
        Optional<Question> question = questionService.findById(id);
        QuestionResponse founded = conversionQuestion.convert(question.get(), QuestionResponse.class);
        logger.info("Question" + founded.toString());
        return founded;
    }

    public QuestionResponse convertToFindByTitle(String title) {
        Optional<Question> question = questionService.findByTitle(title);
        QuestionResponse founded = conversionQuestion.convert(question.get(), QuestionResponse.class);
        logger.info("Question: " + founded.toString());
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
