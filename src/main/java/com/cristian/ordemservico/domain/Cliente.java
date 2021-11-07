package com.cristian.ordemservico.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa implements Serializable{
	private final static long serialVersionUID = 1L;
	
	
	@OneToMany(mappedBy = "cliente")  //um cliente para muitas ordens de serviço que foi mapeado por
	//@JoinColumn(name = "cliente_id") na classe OS
	private List<OS> list = new ArrayList<>();

	public Cliente() {
		super();

	}

	public Cliente(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);

	}

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}
	
	

}
