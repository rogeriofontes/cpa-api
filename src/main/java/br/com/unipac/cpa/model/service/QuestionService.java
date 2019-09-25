package br.com.unipac.cpa.model.service;

import br.com.unipac.cpa.model.domain.Question;

import java.util.Optional;

public interface QuestionService extends CrudService<Question, Long>{
    Optional<Question> findByTitle(String title);
}
