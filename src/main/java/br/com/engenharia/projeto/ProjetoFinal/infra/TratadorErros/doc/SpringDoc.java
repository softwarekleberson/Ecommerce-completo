package br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SpringDoc {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI();
	}
}
