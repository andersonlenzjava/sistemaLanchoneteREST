package com.lanchonete.sistema.repository.ingredientes;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.sistema.model.ingrediente.LancheRecheio;

@Repository
public interface LancheRecheioRepository extends JpaRepository<LancheRecheio, Long> {

	Page<LancheRecheio> findByTipoRecheioIgnoreCase(String tipoRecheio, Pageable paginacao);

	Optional<LancheRecheio> findByTipoRecheioIgnoreCase(String tipoRecheio);


}
