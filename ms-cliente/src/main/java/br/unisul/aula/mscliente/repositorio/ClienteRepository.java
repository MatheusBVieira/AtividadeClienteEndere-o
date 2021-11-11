package br.unisul.aula.mscliente.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.unisul.aula.mscliente.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("select c from Cliente c where c.endereco_id=:enderecoId")
	List<Cliente> FindByEndereco_id(Long enderecoId);

}
