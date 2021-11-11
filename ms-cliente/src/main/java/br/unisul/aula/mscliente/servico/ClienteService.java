package br.unisul.aula.mscliente.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.aula.mscliente.dto.ClienteDTO;
import br.unisul.aula.mscliente.dto.ClienteResumoDTO;
import br.unisul.aula.mscliente.dto.EnderecoDTO;
import br.unisul.aula.mscliente.modelo.Cliente;
import br.unisul.aula.mscliente.repositorio.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	/*
	 * 
	 * @Autowired private RestTemplate clienteRaimbow;
	 */

	@Autowired
	private EnderecoClient enderecoClient;

	public List<ClienteDTO> listarTodos() {
		List<ClienteDTO> dtos = new ArrayList<>();
		for (Cliente cliente : clienteRepository.findAll()) {
			EnderecoDTO enderecoDTO = buscarEnderecoPorID(cliente.getEndereco_id());
			ClienteDTO clienteDTO = new ClienteDTO(cliente, enderecoDTO);
			dtos.add(clienteDTO);
		}
		return dtos;
	}

	private EnderecoDTO buscarEnderecoPorID(Long id) {
		return enderecoClient.buscarPorId(id);
	}

	private EnderecoDTO buscarEnderecoPorCep(Integer cep) {
		return enderecoClient.buscarPorCep(cep);
	}
	/*
	 * 
	 * private EnderecoDTO buscarEnderecoPorID(Long id) {
	 * ResponseEntity<EnderecoDTO> exchange =
	 * clienteRaimbow.exchange("http://endereco/end/id/" + id, HttpMethod.GET, null,
	 * EnderecoDTO.class); return exchange.getBody(); } private EnderecoDTO
	 * buscarEnderecoPorCep(Integer cep) { ResponseEntity<EnderecoDTO> exchange =
	 * clienteRaimbow.exchange("http://endereco/end/" + cep, HttpMethod.GET, null,
	 * EnderecoDTO.class); return exchange.getBody(); }
	 */

	public void registrarCliente(ClienteDTO dto) {
		dto.setEndereco(buscarEnderecoPorCep(dto.getCep()));
		Cliente cliente = dto.converterParaCliente();
		clienteRepository.save(cliente);
	}

	public List<ClienteResumoDTO> listarPorEndereco(Long id) {
		List<Cliente> clientes = clienteRepository.FindByEndereco_id(id);

		List<ClienteResumoDTO> dtos = new ArrayList<>();
		for (Cliente cliente : clientes) {
			ClienteResumoDTO clienteDto = new ClienteResumoDTO(cliente);
			dtos.add(clienteDto);
		}
		return dtos;

	}
}
