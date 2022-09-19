package com.lanchonete.sistema.repository.ingredientes;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.sistema.model.ingrediente.PizzaRecheio;

@Repository
public interface PizzaRecheioRepository extends JpaRepository<PizzaRecheio, Long> {

	Page<PizzaRecheio> findByTipoRecheioIgnoreCase(String tipoRecheio, Pageable paginacao);

	Optional<PizzaRecheio> findByTipoRecheioIgnoreCase(String tipoMolho);

}
