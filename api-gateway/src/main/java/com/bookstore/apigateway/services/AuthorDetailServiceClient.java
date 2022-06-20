package com.bookstore.apigateway.services;

import com.bookstore.apigateway.controllers.AuthorDetailDTO;
import com.bookstore.apigateway.controllers.BookDetailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthorDetailServiceClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String authorDetailBasedUrl;

    public AuthorDetailServiceClient(
            ObjectMapper objectMapper,
            RestTemplate restTemplate,
            @Value("${app.authordetail-service.host}") String authorDetailServiceHost,
            @Value("${app.authordetail-service.port}") String authorDetailServicePort
    ) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

        authorDetailBasedUrl = "http://" + authorDetailServiceHost + ":" + authorDetailServicePort + "/api";
    }

    public AuthorDetailDTO getAuthorDetail(String Id){
        try {
            String url = authorDetailBasedUrl + "/author" + "/" + Id;
            AuthorDetailDTO authorDetailDTO = restTemplate.getForObject(url, AuthorDetailDTO.class);
            return authorDetailDTO;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public List<AuthorDetailDTO> getAllAuthors(){
        try {
            String url = authorDetailBasedUrl + "/authors";
            ResponseEntity<List<AuthorDetailDTO>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<AuthorDetailDTO>>() {}
                    );
            List<AuthorDetailDTO> authorDetailDTOS = responseEntity.getBody();
            return authorDetailDTOS;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public AuthorDetailDTO setAuthor(AuthorDetailDTO authorDetailDTO){
        try {
            String url = authorDetailBasedUrl + "/author";
            AuthorDetailDTO response = restTemplate.postForObject(url, authorDetailDTO, AuthorDetailDTO.class);
            return response;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public AuthorDetailDTO deleteAuthor(String Id){
        try {
            String url = authorDetailBasedUrl + "/author" + "/" + Id;
            AuthorDetailDTO authorDetailDTO = restTemplate.getForObject(url, AuthorDetailDTO.class);
            restTemplate.delete(url, AuthorDetailDTO.class);
            return authorDetailDTO;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public AuthorDetailDTO updateAuthor(AuthorDetailDTO authorDetailDTO, String Id){
        try {
            String url = authorDetailBasedUrl + "/author" + "/" + Id;
            restTemplate.put(url, authorDetailDTO);
            return authorDetailDTO;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
}
