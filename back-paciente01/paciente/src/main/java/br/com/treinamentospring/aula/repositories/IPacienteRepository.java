package br.com.treinamentospring.aula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.treinamentospring.aula.entities.Paciente;


@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer>{

}
