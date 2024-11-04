package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record LivroConsultaDto(
		
		BigDecimal precoMenor,
        BigDecimal precoMaior,
        LocalDate dataMenor,
        LocalDate dataMaior,
        String titulo,
        String isbn,
        Integer paginas,
        String editora,
        String edicao,
        List<Long> autorIds,
        List<Long> categoriaIds,
        Double alturaMaior,
        Double alturaMenor,
        Double larguraMaior,
        Double larguraMenor,
        Double pesoMaior,
        Double pesoMenor,
        Double profundidadeMaior,
        Double profundidadeMenor
		
		) 
	{
}
