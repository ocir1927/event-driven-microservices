package com.costin.disertatie.stock.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
public class TiingoStockPricePooling {

    private static String cachedResponse;
//
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Value("${stockList}")
    private String stockList;

    private final String clientUrl = "https://api.tiingo.com/iex";

    public String createPoolingUrl() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> headersEntity = new HttpEntity<String>(headers);
//        System.out.println("Stock list is: " + stockList);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(clientUrl)
                .queryParam("token", "8cf64985b147336926b8560b2e200bb4793fb7e8")
                .queryParam("tickers", stockList);
        return builder.toUriString();
    }

    @Scheduled(fixedDelay = 7500L)
    public void poolStockValues(){
        System.out.println("Calling external api");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> headersEntity = new HttpEntity<String>(headers);

        String respone = restTemplate.exchange(createPoolingUrl(), HttpMethod.GET,headersEntity,String.class).getBody();
        saveStockDataToCache(respone);
        this.simpMessagingTemplate.convertAndSend("/topic/stock-update",respone);
        System.out.println("The respone is: " + respone);
    }

    public void saveStockDataToCache(String response) {
        this.cachedResponse = response;
    }

    public static String getCachedRespone(){
        return cachedResponse;
    }


}
