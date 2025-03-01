package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CobrancaRepository;

@Service
public class ServiceCobranca {

	public static Optional<Cobranca> verificaExistenciaCobranca(CobrancaRepository cobrancaRepository, Long id) {
		System.out.println("Service Cobranca");
		Optional<Cobranca> cobranca = cobrancaRepository.findCobrancaPrincipalByClienteId(id);
		if(cobranca.isEmpty()) {
			throw new ValidacaoPagamentoServiceException("Cobrança principal não encontrada");
		}
		return cobranca;
	}
}
