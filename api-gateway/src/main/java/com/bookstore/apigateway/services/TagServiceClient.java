package com.bookstore.apigateway.services;

import com.bookstore.apigateway.controllers.BookDetailDTO;
import com.bookstore.apigateway.controllers.ReviewDTO;
import com.bookstore.apigateway.controllers.TagDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TagServiceClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String recommendationBasedUrl;

    public TagServiceClient(RestTemplate restTemplate,
                               ObjectMapper objectMapper,
                               @Value("${app.bookrecommendation-service.host}") String recommendationServiceHost,
                               @Value("${app.bookrecommendation-service.port}") String recommendationServicePort
    ) {

        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

        recommendationBasedUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort + "/api";
    }

    public List<TagDTO> getTags(String Id){
        try {
            String url = recommendationBasedUrl + "/tags" + "/" + Id;
            ResponseEntity<List<TagDTO>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<TagDTO>>() {}
                    );
            List<TagDTO> tagDTOS = responseEntity.getBody();
            return tagDTOS;
        }catch (HttpClientErrorException ex){
            System.out.println(ex);
            return null;
        }
    }
    public List<TagDTO> getAllTags(){
        try {
            String url = recommendationBasedUrl + "/tags";
            ResponseEntity<List<TagDTO>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<TagDTO>>() {}
                    );
            List<TagDTO> tagDTOS = responseEntity.getBody();
            return tagDTOS;
        }catch (HttpClientErrorException ex){
            System.out.println(ex);
            return null;
        }
    }
    public TagDTO setTag(TagDTO tagDTO){
        try {
            String url = recommendationBasedUrl + "/tag";
            TagDTO response = restTemplate.postForObject(url, tagDTO, TagDTO.class);
            return response;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public List<String> getUniqueTags(){
        try {
            String url = recommendationBasedUrl + "/tags/unique";
            ResponseEntity<List<String>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<String>>() {}
                    );
            List<String> uniqueTags = responseEntity.getBody();
            return uniqueTags;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public List<String> getRecommended(String Id){
        try {
            String url = recommendationBasedUrl + "/tags" + "/" + Id + "/recommended";
            ResponseEntity<List<String>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<String>>() {}
                    );
            List<String> recommendedBooks = responseEntity.getBody();
            return recommendedBooks;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public TagDTO deleteTag(String Id){
        try {
            String url = recommendationBasedUrl + "/tag" + "/" + Id;
            TagDTO tagDTO = restTemplate.getForObject(url, TagDTO.class);
            restTemplate.delete(url, TagDTO.class);
            return tagDTO;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public TagDTO updateTag(TagDTO tagDTO, String Id){
        try {
            String url = recommendationBasedUrl + "/tag" + "/" + Id;
            restTemplate.put(url, tagDTO);
            return tagDTO;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
}
