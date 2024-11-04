package br.com.engenharia.projeto.ProjetoFinal.services.openia;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleGeminiService {

    private final String API_KEY = "";
    private final String BARD_API_URL = "https://bard.googleapis.com/v1/queries";  
    
    private final RestTemplate restTemplate = new RestTemplate();  

    public String sendToBard(String query) {
        
    	HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        String body = "{ \"input\": \"" + query + "\" }";
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(BARD_API_URL, HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }
}
