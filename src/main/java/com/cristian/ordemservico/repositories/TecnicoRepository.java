package com.cristian.ordemservico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristian.ordemservico.domain.Tecnico;


/*JpaRepository se comunica com a base de dados */ 
@Repository /*Informa que ele é um repositório*/
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
    
	

}
