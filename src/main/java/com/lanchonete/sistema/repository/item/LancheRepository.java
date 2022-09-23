package com.lanchonete.sistema.repository.item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lanchonete.sistema.model.item.Lanche;

public interface LancheRepository extends JpaRepository<Lanche, Long>  {

}
