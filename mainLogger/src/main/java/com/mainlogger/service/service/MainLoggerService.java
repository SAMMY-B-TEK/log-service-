package com.mainlogger.service.service;

import com.github.javafaker.Faker;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class MainLoggerService {

    private static final Logger log = LoggerFactory.getLogger(MainLoggerService.class);

    @Autowired
    private RestTemplate exchange;

    @Value("${external.service.host}")
    private String externalServiceHost;
    @Value("${external.service.username}")
    private String username;

    @Value("${external.service.password}")
    private String password;

    public List<String> getEmailsByFilter(int request) throws BadRequestException {
        log.debug("getEmailsByFilter method start");

        int loopLimit = (request == 0) ? 10 : request;
        log.info("user request for {} emails", loopLimit);
        if(loopLimit < 0){
            log.error("Bad Request, request cannot be negative");
            throw new BadRequestException("request can not be negative");
        }
        List<String> listOfEmails = new ArrayList<>();
        Faker instance = Faker.instance();
        for (int i = 0; i < loopLimit; i++) {
            listOfEmails.add(instance.internet().emailAddress());
        }
        log.debug("getEmailsByFilter method ends");
        return listOfEmails;
    }

    public String getMobileNumberFromExternalService(String email) {

        String url = externalServiceHost + "/sec-logger/mobile/{email}";

        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
        headers.set("Authorization", encodedAuth);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return exchange.exchange(url, HttpMethod.GET, entity, String.class, email).getBody();
    }
}
