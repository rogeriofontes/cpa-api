package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.ProfessorDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorDisciplineRepository extends JpaRepository<ProfessorDiscipline, Long> {
}
