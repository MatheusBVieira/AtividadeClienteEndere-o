package br.unisul.aula.msendereco.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.unisul.aula.msendereco.dtos.ClienteResumoDTO;

@FeignClient("cliente")
public interface ClienteClient {

	@GetMapping("/cli/{enderecoId}")
	List<ClienteResumoDTO> listarClientesPorEndereco(@PathVariable Long enderecoId);

}
