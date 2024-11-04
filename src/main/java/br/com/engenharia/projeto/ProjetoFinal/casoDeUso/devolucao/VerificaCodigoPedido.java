package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.RepositorioDeDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;

@Service
public class VerificaCodigoPedido implements IstrategyDevolucao {

    @Autowired
    private RepositorioDeDevolucao repositorioDeDevolucao;

    private static final String MENSAGEM_ERRO = "O pedido com o código informado já possui uma devolução associada.";

    @Override
    public void processar(DadosCadastroDevolucao dados) {
        Optional<Devolucao> devolucao = repositorioDeDevolucao.verificaCodigoPedido(dados.codigoPedido());
        
        if (devolucao.isPresent()) {
            throw new ValidacaoException(MENSAGEM_ERRO);
        }
    }
}
