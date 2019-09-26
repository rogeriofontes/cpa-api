package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.model.domain.Choice;
import br.com.unipac.cpa.model.repository.ChoiceRepository;
import br.com.unipac.cpa.model.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    @Autowired
    private ChoiceRepository choiceRepository;

    @Override
    public Choice save(Choice choice) {
        return choiceRepository.save(choice);
    }

    @Override
    public Choice edit(Long id, Choice choice) {
        choice.update(id, choice);
        return choiceRepository.save(choice);
    }

    @Override
    @Cacheable(Constants.CHOICES_IN_CACHE)
    public List<Choice> listAll() {
        return choiceRepository.findAll();
    }

    @Override
    @Cacheable(Constants.CHOICES_IN_CACHE)
    public Page<Choice> findAllPageable(Pageable pageable) {
        return choiceRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.CHOICES_IN_CACHE)
    public Optional<Choice> findById(Long id) {
        return choiceRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            choiceRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
