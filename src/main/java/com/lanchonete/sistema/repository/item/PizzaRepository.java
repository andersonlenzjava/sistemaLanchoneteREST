package com.lanchonete.sistema.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lanchonete.sistema.model.item.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>  {

}
