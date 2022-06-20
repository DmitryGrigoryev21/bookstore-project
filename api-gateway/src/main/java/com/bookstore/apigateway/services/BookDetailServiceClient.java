package com.bookstore.apigateway.services;

import com.bookstore.apigateway.controllers.BookAggregateDTO;
import com.bookstore.apigateway.controllers.BookDetailDTO;
import com.bookstore.apigateway.controllers.ReviewDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
@Slf4j
public class BookDetailServiceClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String bookDetailBasedUrl;

    public BookDetailServiceClient(RestTemplate restTemplate,
                                   ObjectMapper objectMapper,
                                   @Value("${app.bookdetail-service.host}") String bookDetailsServiceHost,
                                   @Value("${app.bookdetail-service.port}") String bookDetailsServicePort
    ) {

        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

        bookDetailBasedUrl = "http://" + bookDetailsServiceHost + ":" + bookDetailsServicePort + "/api";
    }

    public BookAggregateDTO getBookDetail(String Id){
        try {
            String url = bookDetailBasedUrl + "/book" + "/" + Id;
            BookAggregateDTO bookAggregateDTO = restTemplate.getForObject(url, BookAggregateDTO.class);
            return bookAggregateDTO;
        }catch (HttpClientErrorException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    public List<BookAggregateDTO> getAllBookDetail(){
        try {
            String url = bookDetailBasedUrl + "/books" + "/detailed";
            ResponseEntity<List<BookAggregateDTO>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<BookAggregateDTO>>() {}
                    );
            List<BookAggregateDTO> bookAggregateDTOS = responseEntity.getBody();
            return bookAggregateDTOS;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }

    //book
    public BookDetailDTO getBookOnlyDetail(String Id){
        try {
            String url = bookDetailBasedUrl + "/book" + "/" + Id;
            BookDetailDTO bookDetailDTO = restTemplate.getForObject(url, BookDetailDTO.class);
            return bookDetailDTO;
        }catch (HttpClientErrorException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }
    public List<BookDetailDTO> getAllBooks(){
        try {
            String url = bookDetailBasedUrl + "/books" + "/detailed";
            ResponseEntity<List<BookDetailDTO>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<BookDetailDTO>>() {}
                    );
            List<BookDetailDTO> bookDetailDTOS = responseEntity.getBody();
            return bookDetailDTOS;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public BookDetailDTO setBook(BookDetailDTO bookDetailDTO){
        try {
            String url = bookDetailBasedUrl + "/book";
            BookDetailDTO response = restTemplate.postForObject(url, bookDetailDTO, BookDetailDTO.class);
            return response;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public BookDetailDTO deleteBook(String Id){
        try {
            String url = bookDetailBasedUrl + "/book" + "/" + Id;
            BookDetailDTO bookDetailDTO = restTemplate.getForObject(url, BookDetailDTO.class);
            restTemplate.delete(url, BookDetailDTO.class);
            return bookDetailDTO;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
    public BookDetailDTO updateBook(BookDetailDTO bookDetailDTO, String Id){
        try {
            String url = bookDetailBasedUrl + "/book" + "/" + Id;
            restTemplate.put(url, bookDetailDTO);
            return bookDetailDTO;
        }catch (HttpClientErrorException ex){
            return null;
        }
    }
}
