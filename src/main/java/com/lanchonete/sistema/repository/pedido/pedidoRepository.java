package com.lanchonete.sistema.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.sistema.model.pedido.Pedido;

@Repository
public interface pedidoRepository extends JpaRepository<Pedido, Long>{

}
