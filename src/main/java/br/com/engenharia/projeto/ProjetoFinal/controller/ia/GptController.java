package br.com.engenharia.projeto.ProjetoFinal.controller.ia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engenharia.projeto.ProjetoFinal.dtos.openia.ChatRequestDTO;
import br.com.engenharia.projeto.ProjetoFinal.services.openia.GoogleGeminiService;
import br.com.engenharia.projeto.ProjetoFinal.services.openia.OpenAiService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("chat")
@CrossOrigin(origins = "*")
public class GptController {

	@Autowired
	private OpenAiService openAiService;

	@Autowired
	private GoogleGeminiService geminiService;

	@PostMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestBody ChatRequestDTO request) {
	   String userMessage = request.mensagem();
	   String chatGptResponse = openAiService.getChatGptResponse(userMessage);

	   return ResponseEntity.ok(chatGptResponse);
	}
	
	@PostMapping("/ask-bard")
	public String askBard(@RequestBody ChatRequestDTO request) {
	   return geminiService.sendToBard(request.mensagem());
	}
}
