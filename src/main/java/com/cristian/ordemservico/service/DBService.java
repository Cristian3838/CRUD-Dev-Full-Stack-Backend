package com.cristian.ordemservico.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristian.ordemservico.domain.Cliente;
import com.cristian.ordemservico.domain.OS;
import com.cristian.ordemservico.domain.Tecnico;
import com.cristian.ordemservico.domain.enuns.Prioridade;
import com.cristian.ordemservico.domain.enuns.Status;
import com.cristian.ordemservico.repositories.ClienteRepository;
import com.cristian.ordemservico.repositories.OSRepository;
import com.cristian.ordemservico.repositories.TecnicoRepository;

@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Carlos Augusto", "000.530.040-19", "(16)99372-3333");
		Tecnico t2 = new Tecnico(null, "Dona Maria", "159.847.220-89", "(16)99372-4444");
		Cliente c1 = new Cliente(null, "Maria Augusta", "816.968.750-05", "(16)99999-1122");

		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}

}
