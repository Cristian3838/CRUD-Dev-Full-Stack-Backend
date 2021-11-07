package com.cristian.ordemservico.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cristian.ordemservico.domain.Tecnico;
import com.cristian.ordemservico.dtos.TecnicoDTO;
import com.cristian.ordemservico.service.TecnicoService;


@CrossOrigin("*") /*Pode receber requisições de multiplas fontes e multiplas fontes podem consumir a API*/
@RestController /* Recebe as requisições http */
@RequestMapping(value = "/tecnicos") /* Seta um endpoint inicial */

public class TecnicoResource {

	@Autowired
	private TecnicoService service;

	@GetMapping(value = "/{id}")

	/* do tipo tecnico chamado de findById */
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = service.findById(id);
		TecnicoDTO objDTO = new TecnicoDTO(obj);
		return ResponseEntity.ok().body(objDTO);

	}

	/* Método que faz a listagem de toda a base de dados */

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<Tecnico> list = service.findAll();
		List<TecnicoDTO> listDTO = new ArrayList<>();

		list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));

		return ResponseEntity.ok().body(listDTO);
	}
	
	
	/*Cria um técnico*/
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
		Tecnico newObj = service.create(objDTO);
		
		
		//Para transforma um newObj em Uri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	/*Atualiza um técnico */
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
		TecnicoDTO newObj = new TecnicoDTO(service.update(id, objDTO));
		
		return ResponseEntity.ok().body(newObj);
	}
	
	/*Delete técnico*/
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
