package com.cristian.ordemservico.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristian.ordemservico.domain.Cliente;
import com.cristian.ordemservico.domain.OS;
import com.cristian.ordemservico.domain.Tecnico;
import com.cristian.ordemservico.domain.enuns.Prioridade;
import com.cristian.ordemservico.domain.enuns.Status;
import com.cristian.ordemservico.dtos.OSDTO;
import com.cristian.ordemservico.repositories.OSRepository;
import com.cristian.ordemservico.service.exception.ObjectNotFoundException;

@Service
public class OsService {
	
	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! id: "+id+",Tipo: "
				+ OS.class.getName()));
	}
	
	public List<OS> findAll(){
		return repository.findAll(); 
	}

	public OS create(@Valid OSDTO obj) {
		
		return fromDTO(obj); 
	}
	

	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	private OS fromDTO(OSDTO obj) { 
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod()));
		newObj.setStatus(Status.toEnum(obj.getStatus().getCod()));
		
		//Realizando a busca pelo ID do técnico e do cliente
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente()); 
		
		newObj.setCliente(cli);
		newObj.setTecnico(tec);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	} 


}
