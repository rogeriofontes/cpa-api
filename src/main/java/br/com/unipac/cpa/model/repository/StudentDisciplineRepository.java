package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.StudentDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDisciplineRepository extends JpaRepository<StudentDiscipline, Long> {
}
