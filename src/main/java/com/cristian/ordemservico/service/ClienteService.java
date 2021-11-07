package com.cristian.ordemservico.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristian.ordemservico.domain.Pessoa;
import com.cristian.ordemservico.domain.Cliente;
import com.cristian.ordemservico.dtos.ClienteDTO;
import com.cristian.ordemservico.repositories.PessoaRepository;
import com.cristian.ordemservico.repositories.ClienteRepository;
import com.cristian.ordemservico.service.exception.DataIntegratyViolationException;
import com.cristian.ordemservico.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {

		// busca pelo id do Cliente ou nulo
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()));

	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/* Cria um Cliente */

	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}

	/* Atualiza um Cliente */ 

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id); // Verifica se há um técnico com esse id

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setTelefone(objDTO.getTelefone());
		oldObj.setCpf(objDTO.getCpf());

		return repository.save(oldObj);

	}

	/* Deleta um Cliente */

	public void delete(Integer id) {
		Cliente obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Pessoa possui ordens de serviço, não pode ser deletado!");
		}
		
		
		repository.deleteById(id);

	} 

	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
