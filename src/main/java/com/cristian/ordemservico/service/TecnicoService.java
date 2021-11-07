package com.cristian.ordemservico.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristian.ordemservico.domain.Pessoa;
import com.cristian.ordemservico.domain.Tecnico;
import com.cristian.ordemservico.dtos.TecnicoDTO;
import com.cristian.ordemservico.repositories.PessoaRepository;
import com.cristian.ordemservico.repositories.TecnicoRepository;
import com.cristian.ordemservico.service.exception.DataIntegratyViolationException;
import com.cristian.ordemservico.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {

		// busca pelo id do técnico ou nulo
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo: " + Tecnico.class.getName()));

	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	/* Cria um técnico */

	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}

	/* Atualiza um técnico */

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id); // Verifica se há um técnico com esse id

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setTelefone(objDTO.getTelefone());
		oldObj.setCpf(objDTO.getCpf());

		return repository.save(oldObj);

	}

	/* Deleta um técnico */

	public void delete(Integer id) {
		Tecnico obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui ordens de serviço, não pode ser deletado!");
		}
		
		
		repository.deleteById(id); 

	} 

	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
