package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}

