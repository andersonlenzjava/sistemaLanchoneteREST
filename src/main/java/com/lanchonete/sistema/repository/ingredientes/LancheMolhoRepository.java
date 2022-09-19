package com.lanchonete.sistema.repository.ingredientes;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.sistema.model.ingrediente.LancheMolho;

@Repository
public interface LancheMolhoRepository extends JpaRepository<LancheMolho, Long> {

	Page<LancheMolho> findByTipoMolhoIgnoreCase(String tipoMolho, Pageable paginacao);

	Optional<LancheMolho> findByTipoMolhoIgnoreCase(String tipoMolho);

}
