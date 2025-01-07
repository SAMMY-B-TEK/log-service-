package com.mainlogger.service.controller;


import com.mainlogger.service.service.MainLoggerService;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainLoggerController {
    private static final Logger log = LoggerFactory.getLogger(MainLoggerController.class);
    @Autowired
    MainLoggerService mainLoggerService;

    @GetMapping("/email")
    public ResponseEntity<List<String>> getEmails(@RequestParam(defaultValue = "0") int request) throws BadRequestException {
        log.info("getEmails method start");
        List<String> result = mainLoggerService.getEmailsByFilter(request);
        log.info("getEmails method ends");
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);

    }

    @GetMapping("mobile/{email}")
    public ResponseEntity<String> getMobileNumberByEmail(@PathVariable ("email") String email) throws BadRequestException {
        log.info("getMobileNumberByEmail method start");
        String result = mainLoggerService.getMobileNumberFromExternalService(email);
        log.info("getMobileNumberByEmail method ends");
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);

    }

}
