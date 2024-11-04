package br.com.engenharia.projeto.ProjetoFinal.controller.cliente;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping
	public String hello() {
		return "oi tudo certo aqui, foi essa porra";
	}
}
