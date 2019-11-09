package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Evaluation;
import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.model.repository.EvaluationRepository;
import br.com.unipac.cpa.web.dto.request.QuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuestionRequestConverter implements Converter<QuestionRequest, Question> {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public Question convert(QuestionRequest questionRequest) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(questionRequest.getEvaluationId());
        return Question.builder()
                .title(questionRequest.getTitle())
                .description(questionRequest.getDescription())
                .evaluation(evaluation.isPresent() ? evaluation.get() : new Evaluation()).build();
    }
}
