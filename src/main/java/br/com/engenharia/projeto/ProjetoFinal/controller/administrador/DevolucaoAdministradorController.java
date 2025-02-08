package br.com.engenharia.projeto.ProjetoFinal.controller.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosAtualizacaoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.RepositorioDeDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceAceitarDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceRecusarDevolucao;
import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "*")
public class DevolucaoAdministradorController {

    @Autowired
    private ServiceRecusarDevolucao serviceRecusarDevolucao;

    @Autowired
    private ServiceAceitarDevolucao serviceAceitarDevolucao;

    @Autowired
    private RepositorioDeDevolucao repositorioDeDevolucao;

    @GetMapping("/devolucoes")
    public ResponseEntity<Page<DadosDetalhamentoTotalDevolucao>> listarTodasAsDevolucoes(Authentication authentication, Pageable pageable) {
    	UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
    	
    	Page<DadosDetalhamentoTotalDevolucao> devolucoes = repositorioDeDevolucao.listarTodasAsDevolucoes(pageable, id);
        return ResponseEntity.ok(devolucoes);
    }

    @PutMapping("/recusar")
    public ResponseEntity<DadosDetalhamentoTotalDevolucao> recusarDevolucoes(@RequestBody @Valid DadosAtualizacaoDevolucao dados) {
        DadosDetalhamentoTotalDevolucao detalhamentoDevolucao = serviceRecusarDevolucao.devolucaoRecusada(dados);
        return ResponseEntity.ok(detalhamentoDevolucao);
    }

    @PutMapping("/aceitar")
    public ResponseEntity<DadosDetalhamentoTotalDevolucao> aceitarDevolucoes(@RequestBody @Valid DadosAtualizacaoDevolucao dados) {
        DadosDetalhamentoTotalDevolucao detalhamentoDevolucao = serviceAceitarDevolucao.devolucaoAceita(dados);
        return ResponseEntity.ok(detalhamentoDevolucao);
    }
}