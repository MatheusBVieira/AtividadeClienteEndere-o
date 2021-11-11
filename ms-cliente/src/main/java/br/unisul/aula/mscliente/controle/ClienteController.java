package br.unisul.aula.mscliente.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisul.aula.mscliente.dto.ClienteDTO;
import br.unisul.aula.mscliente.dto.ClienteResumoDTO;
import br.unisul.aula.mscliente.servico.ClienteService;

@RestController
@RequestMapping("/cli")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping("/")
	public List<ClienteDTO> listarTodosClientes() {
		return service.listarTodos();
	}

	@GetMapping("/{enderecoId}")
	public List<ClienteResumoDTO> listarClientesPorEndereco(@PathVariable Long enderecoId) {
		return service.listarPorEndereco(enderecoId);
	}

	@PostMapping("/")
	public void inserirCliente(@RequestBody ClienteDTO dto) {
		service.registrarCliente(dto);
	}
}
