package com.bookstore.apigateway.services;

import com.bookstore.apigateway.controllers.BookDetailDTO;
import com.bookstore.apigateway.controllers.ReviewDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ReviewServiceClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String reviewBasedUrl;

    public ReviewServiceClient(RestTemplate restTemplate,
                               ObjectMapper objectMapper,
                               @Value("${app.bookreview-service.host}") String reviewServiceHost,
                               @Value("${app.bookreview-service.port}") String reviewServicePort
    ) {

        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

        reviewBasedUrl = "http://" + reviewServiceHost + ":" + reviewServicePort + "/api";
    }

    public List<ReviewDTO> getReviews(String Id){
        try {
            String url = reviewBasedUrl + "/reviews" + "/" + Id;
            ResponseEntity<List<ReviewDTO>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<ReviewDTO>>() {}
                    );
            List<ReviewDTO> reviewDTOS = responseEntity.getBody();
            return reviewDTOS;
        }catch (HttpClientErrorException ex){
            System.out.println(ex);
            return null;
        }
    }
    public List<ReviewDTO> getAllReviews(){
        try {
            String url = reviewBasedUrl + "/reviews";
            ResponseEntity<List<ReviewDTO>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<ReviewDTO>>() {}
                    );
            List<ReviewDTO> reviewDTOS = responseEntity.getBody();
            return reviewDTOS;
        }catch (HttpClientErrorException ex){
            System.out.println(ex);
            return null;
        }
    }
    public ReviewDTO setReview(ReviewDTO reviewDTO){
        try {
            String url = reviewBasedUrl + "/review";
            ReviewDTO response = restTemplate.postForObject(url, reviewDTO, ReviewDTO.class);
            return response;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public double getAverageRating(String Id){
        try {
            String url = reviewBasedUrl + "/review" + "/" + Id + "/average";
            double average = restTemplate.getForObject(url, double.class);
            return average;
        }catch (HttpClientErrorException ex){
            return 0;
        }
    }
    public ReviewDTO deleteReview(String Id){
        try {
            String url = reviewBasedUrl + "/review" + "/" + Id;
            ReviewDTO reviewDTO = restTemplate.getForObject(url, ReviewDTO.class);
            restTemplate.delete(url, ReviewDTO.class);
            return reviewDTO;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public ReviewDTO updateReview(ReviewDTO reviewDTO, String Id){
        try {
            String url = reviewBasedUrl + "/review" + "/" + Id;
            restTemplate.put(url, reviewDTO);
            return reviewDTO;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
}
