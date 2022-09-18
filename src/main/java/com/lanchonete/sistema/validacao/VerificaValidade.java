package com.lanchonete.sistema.validacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.lanchonete.sistema.model.ingrediente.Ingrediente;

public class VerificaValidade {

	public static void verificaValidade(Ingrediente ingrediente, String nome) throws AlgumItemEstaVencidoException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataHojeF = LocalDate.now();
		LocalDate dataValidadeItem = LocalDate.parse(ingrediente.getDataValidade(), formato);

		long diferencaEmDias = ChronoUnit.DAYS.between(dataValidadeItem, dataHojeF);

		if (diferencaEmDias > 4 && diferencaEmDias < 11) {
			// produto esta para vencer
			System.out.println("O produto " + nome + " está para vencer." );
		} else if (diferencaEmDias >= 0) {
			System.out.println("O produto " + nome + " está vencido a " + diferencaEmDias + " dias.");
			// produto vencido
			throw new AlgumItemEstaVencidoException("O item " + nome + " está vencido, A " + diferencaEmDias + " dias!");
		}
	}
}
