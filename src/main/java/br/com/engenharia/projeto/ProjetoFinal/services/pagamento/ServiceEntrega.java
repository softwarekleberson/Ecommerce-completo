package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.EntregaRepository;

@Service
public class ServiceEntrega {

    public static Optional<Entrega> verificaExistenciaEntrega(EntregaRepository entregaRepository, Long id) {
        Optional<Entrega> entrega = entregaRepository.findEntregaPrincipalByClienteId(id);
        
        if (entrega.isEmpty()) {
            throw new ValidacaoPagamentoServiceException("Entrega principal não encontrada");
        }
        return entrega;
    }
}
