package br.unisul.aula.msendereco.dtos;

import java.util.List;

import br.unisul.aula.msendereco.modelo.Endereco;
import br.unisul.aula.msendereco.modelo.UnidadeFederativa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoClienteDto {

	private String cidade;
	private UnidadeFederativa uf;
	private List<ClienteResumoDTO> clientes;

	public EnderecoClienteDto(Endereco endereco, List<ClienteResumoDTO> clientes) {
		this.cidade = endereco.getCidade();
		this.uf = endereco.getUf();
		this.clientes = clientes;

	}

}
