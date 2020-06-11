package br.com.wirecard.mocks.repository;

import br.com.wirecard.mocks.model.db.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

}