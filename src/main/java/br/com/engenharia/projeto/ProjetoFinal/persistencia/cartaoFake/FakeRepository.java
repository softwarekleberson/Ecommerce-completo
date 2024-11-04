package br.com.engenharia.projeto.ProjetoFinal.persistencia.cartaoFake;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.fake.cartao.CartaoFake;

public interface FakeRepository extends JpaRepository<CartaoFake, Long>{

	CartaoFake getReferenceById(Cartao cartao);


}
