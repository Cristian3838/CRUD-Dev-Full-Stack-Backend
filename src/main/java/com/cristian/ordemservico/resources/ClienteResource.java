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

import com.cristian.ordemservico.domain.Cliente;
import com.cristian.ordemservico.dtos.ClienteDTO;
import com.cristian.ordemservico.service.ClienteService;

@CrossOrigin("*") /*Pode receber requisições de multiplas fontes e multiplas fontes podem consumir a API*/
@RestController /* Recebe as requisições http */
@RequestMapping(value = "/clientes") /* Seta um endpoint inicial */

public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}")

	/* do tipo clientes chamado de findById */
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		Cliente obj = service.findById(id);
		ClienteDTO objDTO = new ClienteDTO(obj);
		return ResponseEntity.ok().body(objDTO);

	}

	/* Método que faz a listagem de toda a base de dados */

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDTO = new ArrayList<>();

		list.forEach(obj -> listDTO.add(new ClienteDTO(obj)));

		return ResponseEntity.ok().body(listDTO);
	}
	
	
	/*Cria um cliente*/
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
		Cliente newObj = service.create(objDTO);
		
		
		//Para transforma um newObj em Uri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	/*Atualiza um cliente */
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
		ClienteDTO newObj = new ClienteDTO(service.update(id, objDTO));
		
		return ResponseEntity.ok().body(newObj);
	}
	
	/*Delete cliente*/
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
