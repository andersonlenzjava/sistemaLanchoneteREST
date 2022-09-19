package com.lanchonete.sistema.repository.ingredientes;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.sistema.model.ingrediente.SalgadinhoTipoPreparo;

@Repository
public interface SalgadinhoTipoPreparoRepository extends JpaRepository<SalgadinhoTipoPreparo, Long> {

	Page<SalgadinhoTipoPreparo> findByTipoPreparoIgnoreCase(String tipoPreparo, Pageable paginacao);

	Optional<SalgadinhoTipoPreparo> findByTipoPreparoIgnoreCase(String tipoPreparo);



}
