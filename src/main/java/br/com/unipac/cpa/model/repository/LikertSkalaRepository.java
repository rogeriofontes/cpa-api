package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.LikertSkala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikertSkalaRepository extends JpaRepository<LikertSkala, Long> {
    Optional<LikertSkala> findByName(String name);
}
