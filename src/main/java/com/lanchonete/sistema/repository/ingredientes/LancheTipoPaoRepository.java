package com.lanchonete.sistema.repository.ingredientes;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;

@Repository
public interface LancheTipoPaoRepository extends JpaRepository<LancheTipoPao, Long> {

	Optional<LancheTipoPao> findByTipoPaoIgnoreCase(String tipoPao);

	Page<LancheTipoPao> findByTipoPaoIgnoreCase(String tipoPao, Pageable paginacao);


}
