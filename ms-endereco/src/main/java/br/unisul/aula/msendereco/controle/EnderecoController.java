package br.unisul.aula.msendereco.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisul.aula.msendereco.dtos.EnderecoClienteDto;
import br.unisul.aula.msendereco.dtos.EnderecoDTO;
import br.unisul.aula.msendereco.dtos.EnderecoViaCEPDTO;
import br.unisul.aula.msendereco.modelo.Endereco;
import br.unisul.aula.msendereco.repositorio.EnderecoRepository;
import br.unisul.aula.msendereco.service.EnderecoService;

@RestController
@RequestMapping("/end")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping("/id/{id}")
	public EnderecoClienteDto listaClientesDaCidade(@PathVariable() Long id) {
		return enderecoService.listaClientes(id);
	}

	@GetMapping("/")
	public List<EnderecoDTO> listarTodosEnderecos() {
		List<EnderecoDTO> dtos = new ArrayList<>();
		for (Endereco endereco : enderecoRepository.findAll()) {
			EnderecoDTO dto = new EnderecoDTO(endereco);
			dtos.add(dto);
		}
		return dtos;
	}

	@GetMapping("/lambda")
	public List<EnderecoDTO> listarTodosEnderecosUsandoLambda() {
		List<Endereco> enderecoList = enderecoRepository.findAll();
		return enderecoList.stream().map(EnderecoDTO::new).collect(Collectors.toCollection(ArrayList::new));
	}

	@GetMapping("/{cep}")
	public EnderecoDTO buscarPorCEP(@PathVariable(name = "cep") Integer cep) {
		return new EnderecoDTO(enderecoRepository.findFirstByCep(cep));
	}

//	@GetMapping("/id/{id}")
//	public EnderecoDTO buscarPorCEP(@PathVariable(name = "id") Long id) {
//		return new EnderecoDTO(enderecoRepository.getById(id));
//	}

	@PostMapping("/")
	public void inserirEndereco(@RequestBody EnderecoDTO dto) {
		enderecoRepository.save(dto.converterParaEndereco());
	}

	@PostMapping("/viacep")
	public void inserirEndereco(@RequestBody EnderecoViaCEPDTO dto) {
		enderecoRepository.save(dto.converterParaEndereco());
	}

}
