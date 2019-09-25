package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.model.domain.Option;
import br.com.unipac.cpa.model.repository.OptionRepository;
import br.com.unipac.cpa.model.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Option save(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public Option edit(Long id, Option option) {
        option.update(id, option);
        return optionRepository.save(option);
    }

    @Override
    public List<Option> listAll() {
        return optionRepository.findAll();
    }

    @Override
    public Page<Option> findAllPageable(Pageable pageable) {
        return optionRepository.findAll(pageable);
    }

    @Override
    public Optional<Option> findById(Long id) {
        return optionRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            optionRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
