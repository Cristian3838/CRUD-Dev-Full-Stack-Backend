package com.cristian.ordemservico.domain.enuns;

public enum Prioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "Alta");

	private Integer cod;
	private String descricao;

	private Prioridade(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Prioridade toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		/*
		 * Verifica para cada prioridade x da classe Prioridade, acessando os valores de
		 * x se o código informado corresponde ao código retorno pelo banco
		 */
		for (Prioridade x : Prioridade.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		/*
		 * Exibe uma mensagem ao usuário passando junto a mensagem o código informadopor
		 * ele
		 */
		throw new IllegalArgumentException("Prioridade inválida!" + cod);

	}

}
