package br.unisul.aula.msendereco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisul.aula.msendereco.modelo.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Endereco findFirstByCep(Integer cep);
}
