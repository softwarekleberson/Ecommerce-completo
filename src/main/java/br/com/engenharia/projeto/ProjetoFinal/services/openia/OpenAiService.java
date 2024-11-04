package br.com.engenharia.projeto.ProjetoFinal.services.openia;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAiService {

    private final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final String API_KEY = "";
    private final RestTemplate restTemplate;

    public OpenAiService() {
        this.restTemplate = new RestTemplate();
    }

    public String getChatGptResponse(String userMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Corpo da requisição JSON
        String requestBody = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + userMessage + "\"}] }";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Fazendo a requisição POST para a API do ChatGPT
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

        // Retornando a resposta do ChatGPT
        return response.getBody();
    }
}
