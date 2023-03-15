package br.com.api.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.api.escola.entity.*;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
