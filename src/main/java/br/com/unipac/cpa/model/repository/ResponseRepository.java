package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
}
