package com.lanchonete.sistema.repository.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.sistema.model.pedido.Pedido;
import com.lanchonete.sistema.model.pedido.StatusPedido;

@Repository
public interface pedidoRepository extends JpaRepository<Pedido, Long>{

	Page<Pedido> findByStatusPedido(StatusPedido status, Pageable paginacao);

}
