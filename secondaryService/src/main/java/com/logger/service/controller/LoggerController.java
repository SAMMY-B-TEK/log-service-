package com.logger.service.controller;

import com.logger.service.service.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sec-logger")
public class LoggerController {
    private static final Logger log = LoggerFactory.getLogger(LoggerController.class);

    @Autowired
    LoggerService loggerService;
    @GetMapping("/mobile/{email}")
    public ResponseEntity<String> getMobileNumber (@PathVariable String email){
        log.debug("getMobileNumber method entered");
        String mobileNumber = loggerService.getMobileNumber();
        log.debug("getMobileNumber method ends");
        return ResponseEntity.status(HttpStatus.OK)
                .body(mobileNumber);

    }
}
