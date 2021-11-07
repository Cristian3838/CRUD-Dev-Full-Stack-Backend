package com.cristian.ordemservico.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")  //um técnico para muitas ordens de serviço que foi mapeado por
	//@JoinColumn(name = "tecnico_id") na classe OS
	private List<OS> list = new ArrayList<>();

	public Tecnico() {
		super();
	}

	// Contrutor da super classe construído usando Generate Construtor from
	// SuperClass

	public Tecnico(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}

}
