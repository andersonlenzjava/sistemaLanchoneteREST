package com.lanchonete.sistema.repository.ingredientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.sistema.model.ingrediente.LancheRecheio;

@Repository
public interface LancheRecheioRepository extends JpaRepository<LancheRecheio, Long> {

}
