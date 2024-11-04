package br.com.engenharia.projeto.ProjetoFinal.services.livro.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivroCompleto;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.LivroConsultaDto;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.LivroConsultaGeralDto;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;

@Service
public class LivroConsultaService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroConsultaService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Page<DadosDetalhamentoLivroCompleto> buscarLivros(LivroConsultaDto livroConsultaDTO, int page, int size) {
        Specification<Livro> spec = Specification
            .where(LivroSpecification.comTitulo(livroConsultaDTO.titulo()))
            .and(LivroSpecification.comProfundidadeMenorQue(livroConsultaDTO.profundidadeMenor()))
            .and(LivroSpecification.comProfundidadeMaiorQue(livroConsultaDTO.profundidadeMaior()))
            .and(LivroSpecification.comPrecoMenorQue(livroConsultaDTO.precoMenor()))
            .and(LivroSpecification.comPrecoMaiorQue(livroConsultaDTO.precoMaior()))
            .and(LivroSpecification.comPesoMenorQue(livroConsultaDTO.pesoMenor()))
            .and(LivroSpecification.comPesoMaiorQue(livroConsultaDTO.pesoMaior()))
            .and(LivroSpecification.comMaisPaginasQue(livroConsultaDTO.paginas()))
            .and(LivroSpecification.comLarguraMenorQue(livroConsultaDTO.larguraMenor()))
            .and(LivroSpecification.comLarguraMaiorQue(livroConsultaDTO.larguraMaior()))
            .and(LivroSpecification.comIsbn(livroConsultaDTO.isbn()))
            .and(LivroSpecification.comEditora(livroConsultaDTO.editora()))
            .and(LivroSpecification.comEdicao(livroConsultaDTO.edicao()))
            .and(LivroSpecification.comDataMenorQue(livroConsultaDTO.dataMenor()))
            .and(LivroSpecification.comDataMaiorQue(livroConsultaDTO.dataMaior()))
            .and(LivroSpecification.comCategorias(livroConsultaDTO.categoriaIds()))
            .and(LivroSpecification.comAutores(livroConsultaDTO.autorIds()))
            .and(LivroSpecification.comAlturaMenorQue(livroConsultaDTO.alturaMenor()))
            .and(LivroSpecification.comAlturaMaiorQue(livroConsultaDTO.alturaMaior()));

        Pageable pageable = PageRequest.of(page, size);

        return livroRepository.findAll(spec, pageable).map(DadosDetalhamentoLivroCompleto::new);
    }
    
    
    public Page<DadosDetalhamentoLivroCompleto> buscarLivrosPorTermoGeral(LivroConsultaGeralDto livroConsultaDTO, int page, int size) {
        String termoDeBusca = livroConsultaDTO.termoDePesquisa();
        
        Specification<Livro> spec = Specification
            .where(LivroSpecification.comTitulo(termoDeBusca))
            .or(LivroSpecification.comEditora(termoDeBusca))
        	.or(LivroSpecification.comEdicao(termoDeBusca))
        	.or(LivroSpecification.comSinopse(termoDeBusca));
        
            Pageable pageable = PageRequest.of(page, size);

        return livroRepository.findAll(spec, pageable).map(DadosDetalhamentoLivroCompleto::new);
    }
}
