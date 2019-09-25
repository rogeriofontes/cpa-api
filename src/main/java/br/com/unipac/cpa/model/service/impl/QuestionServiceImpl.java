package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.model.repository.QuestionRepository;
import br.com.unipac.cpa.model.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question edit(Long id, Question question) {
        question.update(id, question);
        return questionRepository.save(question);
    }

    @Override
    @Cacheable(Constants.QUESTION_IN_CACHE)
    public List<Question> listAll() {
        return questionRepository.findAll();
    }

    @Override
    @Cacheable(Constants.QUESTION_IN_CACHE)
    public Page<Question> findAllPageable(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.QUESTION_IN_CACHE)
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        Optional<Question> result = findById(id);

        if (result.isPresent()) {
            questionRepository.deleteById(id);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public Optional<Question> findByTitle(String title) {
        return questionRepository.findByTitle(title);
    }
}
