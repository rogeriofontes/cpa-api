package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.model.domain.Local;
import br.com.unipac.cpa.model.repository.LocalRepository;
import br.com.unipac.cpa.model.service.LocalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalRepository localRepository;

    @Override
    public Local save(Local local) {
        return localRepository.save(local);
    }

    @Override
    public Local edit(Long id, Local local) {
        local.setId(id);
        return localRepository.save(local);
    }

    @Override
    @Cacheable(Constants.LOCALS_IN_CACHE)
    public List<Local> listAll() {
        return localRepository.findAll();
    }

    @Override
    @Cacheable(Constants.LOCALS_IN_CACHE)
    public Page<Local> findAllPageable(Pageable pageable) {
        return localRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.LOCALS_IN_CACHE)
    public Optional<Local> findById(Long id) {
        return localRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }
}
