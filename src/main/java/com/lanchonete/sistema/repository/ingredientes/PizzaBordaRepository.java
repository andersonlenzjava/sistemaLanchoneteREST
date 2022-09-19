package com.lanchonete.sistema.repository.ingredientes;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.sistema.model.ingrediente.PizzaBorda;

@Repository
public interface PizzaBordaRepository extends JpaRepository<PizzaBorda, Long> {

	Page<PizzaBorda> findByTipoBordaIgnoreCase(String tipoBorda, Pageable paginacao);

	Optional<PizzaBorda> findByTipoBordaIgnoreCase(String tipoBorda);

}
