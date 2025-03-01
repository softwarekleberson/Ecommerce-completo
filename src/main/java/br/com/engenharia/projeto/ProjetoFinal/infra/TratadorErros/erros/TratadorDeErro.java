package br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.AdministradorDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.CartaoDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.ValidacaoCartaoException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.ClienteNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.ValidacaoClienteException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.ValidacaoTelefoneException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.CupomDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.ValidacaoCupomException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.DevolucaoDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.CobrancaDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.EntregaDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.ValidacaoCepException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.ValidacaoEnderecoException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.ValidacaoEstadoException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.ValidacaoLogradouroException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.ValidacaoPaisException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.ValidacaoResidenciaException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.ValidacaoEstoqueException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.ValidacaoFornecedorException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.CarrinhoDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor.AutorDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor.ValidacaoAutorException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria.CategoriaDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria.ValidacaoCategoriaException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem.ImagemDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem.ValidacaoImgException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.LivroServiceExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.ValidacaoDimensaoException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.ValidacaoEdicaoException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.ValidacaoEditoraException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.ValidacaoLivroException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.precificacao.PrecificacaoDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.precificacao.ValidacaoPrecificacaoException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.LogNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.PagamentoDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.PedidoServiceException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.ValidacaoPedidoException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.ValidacaoEmailException;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.ValidacaoNomeException;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ValidacaoAceitaDevolucaoServiceException;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ValidacaoAdmServiceException;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ValidacaoCupomServiceException;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ValidacaoRecusaDevolucaoServiceException;
import br.com.engenharia.projeto.ProjetoFinal.services.cliente.ValidacaoCartaoServiceException;
import br.com.engenharia.projeto.ProjetoFinal.services.cliente.ValidacaoClienteServiceExpetion;
import br.com.engenharia.projeto.ProjetoFinal.services.devolucao.ValidarPedidoDevolucaoServiceException;
import br.com.engenharia.projeto.ProjetoFinal.services.estoque.ValidarEstoqueServiceExpetion;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.ValidacaoLivroServiceExpetion;
import br.com.engenharia.projeto.ProjetoFinal.services.pagamento.ValidacaoPagamentoServiceException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class TratadorDeErro {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
	   return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {	        var erros = ex.getFieldErrors();
	   return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
	   return ResponseEntity.badRequest().body(ex.getMessage());
    }

	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity tratarErroRegraDeNegocio(ValidationException ex) {
	   return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity tratarErro500(Exception ex) {
	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
	 }

	@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handlerErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handlerErrorAcessDenided() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acess denied");
    }
        
    @ExceptionHandler(AdministradorDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(AdministradorDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(CartaoDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(CartaoDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(CupomDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(CupomDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(DevolucaoDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(DevolucaoDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(CobrancaDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(CobrancaDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(EntregaDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(EntregaDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoCepException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoCepException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ClienteNaoEncontradoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(ClienteNaoEncontradoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoCartaoException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoCartaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoTelefoneException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoTelefoneException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoCupomException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoCupomException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoEnderecoException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoEnderecoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoEstadoException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoEstadoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoLogradouroException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoLogradouroException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoPaisException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoPaisException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoResidenciaException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoResidenciaException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoEstoqueException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoEstoqueException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoFornecedorException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoFornecedorException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(CarrinhoDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(CarrinhoDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(AutorDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(AutorDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoAutorException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoAutorException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(CategoriaDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(CategoriaDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoCategoriaException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoCategoriaException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ImagemDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(ImagemDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoImgException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoImgException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(LivroServiceExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(LivroServiceExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoDimensaoException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoDimensaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoEdicaoException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoEdicaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoEditoraException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoEditoraException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoLivroException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoLivroException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(PrecificacaoDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(PrecificacaoDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoPrecificacaoException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoPrecificacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(LogNaoEncontradoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(LogNaoEncontradoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(PagamentoDaoExcecao.class)
    public ResponseEntity handlerErrorBusinesRule(PagamentoDaoExcecao ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(PedidoServiceException.class)
    public ResponseEntity handlerErrorBusinesRule(PedidoServiceException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoPedidoException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoPedidoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoEmailException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoEmailException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoNomeException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoNomeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoAceitaDevolucaoServiceException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoAceitaDevolucaoServiceException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoAdmServiceException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoAdmServiceException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoCupomServiceException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoCupomServiceException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoRecusaDevolucaoServiceException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoRecusaDevolucaoServiceException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoClienteException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoClienteException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidarPedidoDevolucaoServiceException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidarPedidoDevolucaoServiceException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoCartaoServiceException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoCartaoServiceException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
   
    @ExceptionHandler(ValidacaoClienteServiceExpetion.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoClienteServiceExpetion ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidarEstoqueServiceExpetion.class)
    public ResponseEntity handlerErrorBusinesRule(ValidarEstoqueServiceExpetion ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoLivroServiceExpetion.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoLivroServiceExpetion ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(ValidacaoPagamentoServiceException.class)
    public ResponseEntity handlerErrorBusinesRule(ValidacaoPagamentoServiceException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
	private record DadosErroValidacao(String nome, String mensagem){
	    public DadosErroValidacao(FieldError erro) {
	      this(erro.getField(), erro.getDefaultMessage());
	    }
	}
}