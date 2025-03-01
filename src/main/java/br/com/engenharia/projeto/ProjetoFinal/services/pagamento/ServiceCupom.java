package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom.CupomRepositroy;

public class ServiceCupom {

	public static List<Cupom> verificaInformacoesSobreCupom(String idCupom1, String idCupom2,
			CupomRepositroy cupomRepository) {
		
		System.out.println("Service Cupom");
		List<Cupom> cupons = new ArrayList<>();
		if(idCupom1 != null) {
			Optional<Cupom> cupom1 = cupomRepository.findById(idCupom1);
			if(cupom1.isPresent()) {
				cupons.add(cupom1.get());
			}
			else {
				throw new ValidacaoPagamentoServiceException("Cupom 1 não encontrado");
			}
		}
		
		if(idCupom2 != null) {
			Optional<Cupom> cupom2 = cupomRepository.findById(idCupom2);
			if(cupom2.isPresent()) {
				cupons.add(cupom2.get());
			}
			else {
				throw new ValidacaoPagamentoServiceException("Cupom 2 não encontrado");
			}
		}
		
		return cupons;
	}
}
