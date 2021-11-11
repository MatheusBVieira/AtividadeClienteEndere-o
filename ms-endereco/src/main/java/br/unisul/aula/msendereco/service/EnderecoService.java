package br.unisul.aula.msendereco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.aula.msendereco.client.ClienteClient;
import br.unisul.aula.msendereco.dtos.ClienteResumoDTO;
import br.unisul.aula.msendereco.dtos.EnderecoClienteDto;
import br.unisul.aula.msendereco.modelo.Endereco;
import br.unisul.aula.msendereco.repositorio.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private ClienteClient client;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public EnderecoClienteDto listaClientes(Long id) {
		List<ClienteResumoDTO> clientes = client.listarClientesPorEndereco(id);
		Endereco endereco = enderecoRepository.findById(id).get();

		return new EnderecoClienteDto(endereco, clientes);
	}

}
